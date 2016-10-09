/*
 * @(#)InvoiceServiceImpl.java        1.00	7 Oct 2016
 *
 * Copyright 2016 Michele Antonaci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.xpeppers.taxy.core.service.impl;

import it.xpeppers.taxy.core.model.Invoice;
import it.xpeppers.taxy.core.model.Product;
import it.xpeppers.taxy.core.service.InvoiceService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Class <code>InvoiceServiceImpl.java</code> is
 *
 * @author Michele Antonaci antonaci.michele@gmail.com
 * @version 1.00 7 Oct 2016
 *
 */

public class InvoiceServiceImpl implements InvoiceService {

	//Basic sales tax
	private static final BigDecimal BASIC_SALES_TAX = new BigDecimal("10");
	//Sales tax for import duty
	private static final BigDecimal IMPORT_SALES_TAX = new BigDecimal("5");
	private static final BigDecimal ROUND_SCALE = new BigDecimal("0.05");
	
	@Override
	public Invoice calculateInvoice(List<Product> products) {

		Invoice invoice = new Invoice();
		List<Product> productsInvoiced = new ArrayList<Product>();
		BigDecimal salesTax = new BigDecimal("0");
		BigDecimal totalPrice = new BigDecimal("0");

		for (Product product : products) {

			BigDecimal finalPrice;

			if (!product.isImported() && product.getCategory().isTaxeable()) {
				finalPrice = product.getPrice().add(calculateProductBasicSalesTax(product.getPrice()));
			} else if (product.isImported() && product.getCategory().isTaxeable()) {
				finalPrice = product.getPrice().add(
					calculateProductBasicSalesTax(product.getPrice()).add(
						calculateProductImportSalesTax(product.getPrice())));
			} else if (product.isImported() && !product.getCategory().isTaxeable()) {
				finalPrice = product.getPrice().add(calculateProductImportSalesTax(product.getPrice()));
			} else {
				finalPrice = product.getPrice();
			}

			salesTax = salesTax.add(finalPrice.subtract(product.getPrice()));
			totalPrice = totalPrice.add(finalPrice);

			product.setTaxedPrice(finalPrice);
			productsInvoiced.add(product);
		}

		invoice.setProducts(productsInvoiced);
		invoice.setSalesTax(salesTax);
		invoice.setTotalPrice(totalPrice);

		return invoice;
	}

	@Override
	public BigDecimal calculateProductBasicSalesTax(BigDecimal price) {
		return roundSaleTax(price.divide(new BigDecimal(100)).multiply(BASIC_SALES_TAX));
	}

	@Override
	public BigDecimal calculateProductImportSalesTax(BigDecimal price) {
		return roundSaleTax(price.divide(new BigDecimal(100)).multiply(IMPORT_SALES_TAX));
	}

	protected BigDecimal roundSaleTax(BigDecimal price) {
		BigDecimal finalPrice = price.divide(ROUND_SCALE).setScale(0, BigDecimal.ROUND_UP).multiply(ROUND_SCALE);
		return finalPrice;
	}

}
