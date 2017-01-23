package facade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import constants.Constants;
import entity.Clientes_Principal;
import entity.CondicoesPagto;
import entity.ProdutosCodConcatEFDPK;
import entity.RetUnidades;
import entity.Transportadora;
import entity.VwContSPEDExpCCusto;
import entity.VwContSPEDExpDeptoCCusto;
import entity.WsIntItemPurchase;
import entity.WsIntPurchase;
import entity.WsIntPurchaseProdAccounting;
import entity.WsIntRelationship;
import factory.ProdutosFactory;
import util.Util;
import xml.purchase.AccountingOM;
import .xml.purchase.DetOM;
import xml.purchase.PurchaseOM;
import xml.purchase.PurchaseResultOM;

public class PurchaseFacade extends AbstractWsFacade {

	public Boolean validatePurchase(PurchaseOM purchaseOM, PurchaseResultOM purchaseResultOM) throws Exception {
		// Method to validate fields
		ValidationFacade validationFacade = new ValidationFacade("Purchase " + purchaseOM.getInf().getNumber());

		//Validate Purchase Number
		validationFacade.isEmpty(purchaseOM.getInf().getNumber(), "number",	"The field 'number' for 'inf_number' cannot be empty!");

		//Validate Date Purchase
		validationFacade.isEmpty(purchaseOM.getInf().getDate(), "date", "The field 'date' for 'inf_date' cannot be empty!");
		
		//Validate deliveryDate
		validationFacade.isEmpty(purchaseOM.getInf().getDeliveryDate(), "deliveryDate", "The field 'deliveryDate' for 'inf_deliveryDate' cannot be empty!");
		
		//Validate Currency
		validationFacade.isEmpty(purchaseOM.getInf().getCurrency(), "currency", "The field 'currency' for 'inf_currency' cannot be empty!");
		
		// Validate Payment Code
		if (!validationFacade.isEmpty(purchaseOM.getInf().getPayment(), "Payment")) {
			WsIntRelationship relationshipKeyPaym = validationFacade.mustExistRelationship("Ws_Int_PaymentTerm", "code",
					purchaseOM.getInf().getPayment(), "Condicoes_Pagto", "codigoPagto", "");
			if (relationshipKeyPaym != null) {
				String mensagemCustomizada = "The PaymentTerm code '" + purchaseOM.getInf().getPayment()
						+ "' was not persisted on 'Condicoes_Pagto' with the code '"
						+ relationshipKeyPaym.getDestinationValue() + "'!";
				validationFacade.mustExist(new CondicoesPagto(), "codigoPagto",
						relationshipKeyPaym.getDestinationValue(), "", mensagemCustomizada);
				relationshipKeyPaym = null;
			}
		}

		// Validate Transporter Code
		if (!Util.isEmpty(purchaseOM.getInf().getTransporter())
				&& !Util.isEmpty(purchaseOM.getInf().getTransporter().getCode())) {
			if (!validationFacade.isEmpty(purchaseOM.getInf().getTransporter().getCode(), "transp_code",
					"The field 'transp_code' for 'Transporter' cannot be empty!")) {
				WsIntRelationship keyTransp = validationFacade.mustExistRelationship("Ws_Int_Supplier", "code",
						purchaseOM.getInf().getTransporter().getCode(), "Transportadoras", "codigo", "");
				if (keyTransp != null) {
					String mensagemCustomizada = "The Transporter '" + purchaseOM.getInf().getTransporter().getCode()
							+ "' was not persisted on 'Transportadoras' with the key '"
							+ keyTransp.getDestinationValue() + "'!";
					validationFacade.mustExist(new Transportadora(), "codigo", keyTransp.getDestinationValue(), "",
							mensagemCustomizada);
					keyTransp = null;
				}
			}
		}

		// Validate Redispatch code
		if (!Util.isEmpty(purchaseOM.getInf().getRedispatch())
				&& !Util.isEmpty(purchaseOM.getInf().getRedispatch().getCode())) {
			if (!validationFacade.isEmpty(purchaseOM.getInf().getRedispatch().getCode(), "redisp_code",
					"The field 'Redisp_code' for 'Redispatch' cannot be empty!")) {
				WsIntRelationship keyRedisp = validationFacade.mustExistRelationship("Ws_Int_Supplier", "code",
						purchaseOM.getInf().getRedispatch().getCode(), "Transportadoras", "codigo", "");
				if (keyRedisp != null) {
					String mensagemCustomizada = "The Transporter '" + purchaseOM.getInf().getRedispatch().getCode()
							+ "' was not persisted on 'Transportadoras' with the key '"
							+ keyRedisp.getDestinationValue() + "'!";
					validationFacade.mustExist(new Transportadora(), "codigo", keyRedisp.getDestinationValue(), "",
							mensagemCustomizada);
					keyRedisp = null;
				}
			}
		}

		// Validate Supplier
		if (!validationFacade.isEmpty(purchaseOM.getInf().getSupplier().getcode(), "Supplier code")) {
			WsIntRelationship relationshipKeyCustomer = validationFacade.mustExistRelationship("Ws_Int_Supplier",
					"code", purchaseOM.getInf().getSupplier().getcode(), "clientes_principal", "codigo_cliente", "");
			if (relationshipKeyCustomer != null) {
				String mensagemCustomizada = "The Supplier '" + purchaseOM.getInf().getSupplier().getcode()
						+ "' was not persisted on 'Clientes_Principal' with CNPJ '"
						+ relationshipKeyCustomer.getDestinationValue() + "'!";
				validationFacade.mustExist(new Clientes_Principal(), "codigoCliente",
						relationshipKeyCustomer.getDestinationValue(), "Fornecedor", mensagemCustomizada);
				relationshipKeyCustomer = null;
			}
		}

		// Validate Item
		if (purchaseOM.getDetail() != null && purchaseOM.getDetail().size() > 0) {

			BigDecimal itemValor = new BigDecimal(0);
			BigDecimal valorTotal = new BigDecimal(0);

			for (DetOM item : purchaseOM.getDetail()) {

				WsIntRelationship keyProduto = validationFacade.mustExistRelationship("Ws_Int_Product", "code",
						item.getProduct().getCode(), "Produtos", "Codigo", "", "item = " + item.getItem() + ")");
				if (keyProduto != null) {
					// Empresa
					String empresa = new ConstantFacade<String>(String.class).findConstant(Constants.PRODUTOS, "EMPRESA").getValorVarchar();
					// Codigo Produto
					String codProduto = keyProduto.getDestinationValue();
					// Codigo Produto Concat.
					String codProdutoConcat = item.getProduct().getCode();
					ProdutosCodConcatEFDPK produtoPk = new ProdutosCodConcatEFDPK(empresa, codProduto,
							codProdutoConcat);

					if ((new ProdutosFactory().findProdutoConcatByPk(produtoPk)) == null) {
						validationFacade.addError("The Item's from Product Code'" + item.getProduct().getCode()
								+ "' (item = " + item.getItem() + ") was not found!");
					}
					produtoPk = null;
					keyProduto = null;
				}
				if (!validationFacade.isEmpty(item.getProduct().getUnit(),
						"The field 'Unit' for Product '" + item.getProduct().getCode() + "' cannot be empty!")) {
					validationFacade.mustExist(new RetUnidades(), "codigo", item.getProduct().getUnit(), "Unit",
							"The Unit '" + item.getProduct().getUnit() + "' for Product code '"
									+ item.getProduct().getCode() + "' (item = " + item.getItem() + ") was not found!");
				}
				// Quantity
				@SuppressWarnings("unused")
				boolean isQuantityEmpty = false;
				if (!(isQuantityEmpty = validationFacade.isEmpty(item.getProduct().getQuantity(), "quantity",
						"The field 'quantity' for Product '" + item.getProduct().getCode() + "' cannot be empty!"))) {
					if (validationFacade.isNumeric(item.getProduct().getQuantity().toString(), "quantity",
							"The field 'quantity' for Product '" + item.getProduct().getCode()
									+ "' must be numeric!")) {
						validationFacade.moreThanZero(item.getProduct().getQuantity(), "",
								"The field 'quantity' for Product '" + item.getProduct().getCode() + "' (item = "
										+ item.getItem() + ") must have a value greater than zero (0)!");
					}
				}
				// Price
				@SuppressWarnings("unused")
				boolean isPriceEmpty = false;
				if (!(isPriceEmpty = validationFacade.isEmpty(item.getProduct().getPrice(), "price",
						"the field 'price' for Product '" + item.getProduct().getCode() + "' cannot be empty!"))) {
					if (validationFacade.isNumeric(item.getProduct().getPrice().toString(), "price",
							"The field 'price' for Product '" + item.getProduct().getCode() + "' must be numeric!")) {
						validationFacade.moreThanZero(item.getProduct().getPrice(), "",
								"The field 'price' for Product '" + item.getProduct().getCode() + "' (item = "
										+ item.getItem() + ") must have a value greater than zero (0)!");
					}
				}
				if (!validationFacade.isEmpty(item.getProduct().getValue(), "value",
						"The field 'value' for Product '" + item.getProduct().getCode() + "' cannot be empty!")) {
					if (validationFacade.isNumeric(item.getProduct().getValue().toString(), "value",
							"The field 'value' for Product'" + item.getProduct().getCode() + "' must be numeric!")) {
						if (validationFacade.moreThanZero(item.getProduct().getValue(), "",
								"The field 'value' for Product '" + item.getProduct().getCode() + "' (item = "
										+ item.getItem() + ") must have a value greater than zero (0)!")) {
							// Quantity x Price = Value
							if (item.getProduct().getQuantity().multiply(item.getProduct().getPrice())
									.setScale(4, RoundingMode.HALF_UP)
									.equals(item.getProduct().getValue().setScale(4, RoundingMode.HALF_UP)) == false) {
								validationFacade.addError("The field 'value' (" + item.getProduct().getValue()
										+ ") for Product code '" + item.getProduct().getCode() + "' (item = "
										+ item.getItem() + ") must be the exact multiplication of Quantity x Price ("
										+ item.getProduct().getQuantity() + " x " + item.getProduct().getPrice() + " = "
										+ item.getProduct().getQuantity().multiply(item.getProduct().getPrice())
												.setScale(4, RoundingMode.HALF_UP)
										+ ")!");
							}
						}
					}
				}
				itemValor = item.getProduct().getValue();
				valorTotal = (itemValor.add(valorTotal));
				
				for (AccountingOM acc : item.getAccounting()) {	
					
					//Validate costCenter
					if (!validationFacade.isEmpty(acc.getCostCenter(),
							"costCenter' for product '" + item.getProduct().getCode() + "' (item = " + item.getItem())) {						
						validationFacade.mustExist(new VwContSPEDExpDeptoCCusto(), "codDepartCCustoTerc", acc.getCostCenter(), "costCenter",
								"The costCenter '" + acc.getCostCenter() + "' for Product code '"
										+ item.getProduct().getCode() + " (item = " + item.getItem() + ") was not found!");
					}				
					
					//Validate subCostCenter
					if (!validationFacade.isEmpty(acc.getSubCostCenter(),
							"subCostCenter' for product '" + item.getProduct().getCode() + "' (item = " + item.getItem())) {						
						validationFacade.mustExist(new VwContSPEDExpCCusto(), "codCCustoTerc", acc.getSubCostCenter(), "subCostCenter",
								"The subCostCenter '" + acc.getSubCostCenter() + "' for Product code '"
										+ item.getProduct().getCode() + " (item = " + item.getItem() + ") was not found!");
					}
					
					validationFacade.isEmpty(acc.getCostType(), "costType",	"The field 'costType' for 'Accounting' cannot be empty!");
					
					validationFacade.isEmpty(acc.getValue(), "value",	"The field 'value' for 'Accounting' cannot be empty!");
				}

			}			
			
			// validate Total Product Value
			if (!valorTotal.equals(purchaseOM.getTotal().getValueProduct())) {
				validationFacade.addError("The field 'valueProduct' " + purchaseOM.getTotal().getValueProduct()
						+ " must be the sum of all items from 'Purchase'");
			}
		}

		validationFacade.throwValidationExceptions();

		purchaseResultOM.setPurchaseOrderResponse(Boolean.TRUE);
		purchaseResultOM.setErrorCode("100");
		purchaseResultOM.setErrorMessage("Purchase succesfully validated.");

		return Boolean.TRUE;

	}

	//
	public WsIntPurchase persistPurchase(PurchaseOM purchaseOM, PurchaseResultOM purchaseResultOM) throws Exception {
		WsIntPurchase wsPurchase = new WsIntPurchase();

		// Recupera Purchase se existir e exclui
		WsIntPurchase wsPurchaseRecover;
		if ((wsPurchaseRecover = (WsIntPurchase) this.findOneByField(new WsIntPurchase(), "infNumber",
				purchaseOM.getInf().getNumber())) != null) {
			this.deleteObject(wsPurchaseRecover);
			wsPurchaseRecover = null;
		}

		// Inf da Purchase
		wsPurchase.setInfNumber(purchaseOM.getInf().getNumber());
		wsPurchase.setInfDate(purchaseOM.getInf().getDate());
		wsPurchase.setInfDeliveryDate(purchaseOM.getInf().getDeliveryDate());

		// Supplier da Purchase
		wsPurchase.setSupCode(purchaseOM.getInf().getSupplier().getcode());
		wsPurchase.setSuppoNumber(purchaseOM.getInf().getSupplier().getpoNumber());

		// Inf da Purchase
		wsPurchase.setUser_name(purchaseOM.getInf().getUser());
		wsPurchase.setPayment(purchaseOM.getInf().getPayment());
		wsPurchase.setCurrency(purchaseOM.getInf().getCurrency());

		// Transporter da Purchase
		wsPurchase.setTranspCode(purchaseOM.getInf().getTransporter().getCode());
		wsPurchase.setTranspFreight(purchaseOM.getInf().getTransporter().getFreight());

		// Transporter da Purchase
		if (Util.isEmpty(purchaseOM.getInf().getRedispatch()) == false) {
			wsPurchase.setRedispCode(purchaseOM.getInf().getRedispatch().getCode());
			wsPurchase.setRedispFreight(purchaseOM.getInf().getRedispatch().getFreight());
		}

		// Total da Purchase
		wsPurchase.setTotValueproduct(purchaseOM.getTotal().getValueProduct());
		wsPurchase.setTot_FreightValue(purchaseOM.getTotal().getFreightValue());
		wsPurchase.setTot_DiscountValue(purchaseOM.getTotal().getDiscountValue());
		wsPurchase.setTot_DiscountPerc(purchaseOM.getTotal().getDiscountPerc());
		wsPurchase.setTot_Value(purchaseOM.getTotal().getValue());

		// Remarks da Purchase
		wsPurchase.setRemarks(purchaseOM.getRemarks());

		// Items da Purchase
		wsPurchase.setWsIntItemPurchases(this.addItemsPurchase(purchaseOM, wsPurchase));

		this.persistObject(wsPurchase);

		purchaseResultOM.setPurchaseOrderResponse(Boolean.TRUE);
		purchaseResultOM.setErrorCode("101");
		purchaseResultOM.setErrorMessage("Purchase succesfully saved.");

		return wsPurchase;
	}

	private Collection<WsIntItemPurchase> addItemsPurchase(PurchaseOM purchaseOM, WsIntPurchase purchase) {
		Collection<WsIntItemPurchase> setItemsPurchase = new ArrayList<WsIntItemPurchase>();
		WsIntItemPurchase itemPurchase;

		for (DetOM item : purchaseOM.getDetail()) {
			itemPurchase = new WsIntItemPurchase();
			itemPurchase.setItem(item.getItem());
			itemPurchase.setProdCode(item.getProduct().getCode());
			itemPurchase.setProdDescription(item.getProduct().getDescription());
			itemPurchase.setProdQuantity(item.getProduct().getQuantity());
			itemPurchase.setUnit(item.getProduct().getUnit());
			itemPurchase.setPrice(item.getProduct().getPrice());
			itemPurchase.setProdDiscountvalue(item.getProduct().getDiscountValue());
			itemPurchase.setProdDiscountperc(item.getProduct().getDiscountPerc());
			itemPurchase.setValue(item.getProduct().getValue());
			itemPurchase.setWsIntPurchase(purchase);
			itemPurchase.setProdAccounting(this.addProdAccounting(item, itemPurchase));
			setItemsPurchase.add(itemPurchase);

			itemPurchase = null;
		}
		return setItemsPurchase;
	}

	private Collection<WsIntPurchaseProdAccounting> addProdAccounting(DetOM item, WsIntItemPurchase itemPurchase) {
		Collection<WsIntPurchaseProdAccounting> setProdAccounting = new HashSet<WsIntPurchaseProdAccounting>();
		WsIntPurchaseProdAccounting accountingProd;

		for (AccountingOM acc : item.getAccounting()) {
			accountingProd = new WsIntPurchaseProdAccounting();
			accountingProd.setCostCenter(acc.getCostCenter());
			accountingProd.setSubCostCenter(acc.getSubCostCenter());
			accountingProd.setCostType(acc.getCostType());
			accountingProd.setValue(acc.getValue());
			accountingProd.setItemPurchase(itemPurchase);

			setProdAccounting.add(accountingProd);
			accountingProd = null;
		}
		return setProdAccounting;
	}

}
