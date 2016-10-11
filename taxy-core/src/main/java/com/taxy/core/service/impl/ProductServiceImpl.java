/*
 * @(#)ProductServiceImpl.java        1.00	11 Oct 2016
 *
 * Copyright (c) 2016 Michele Antonaci
 *
 * Permission is hereby granted, free of charge, to any person obtaining 
 * a copy of this software and associated documentation files (the "Software"), 
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, 
 * and/or sell copies of the Software, and to permit persons to whom the Software 
 * is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in 
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL 
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.taxy.core.service.impl;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.taxy.core.annotation.Log;
import com.taxy.core.exception.TaxyException;
import com.taxy.core.model.Product;
import com.taxy.core.service.ProductService;

/**
 * Class <code>ProductServiceImpl.java</code> is
 *
 * @author Michele Antonaci antonaci.michele@gmail.com
 * @version 1.00 11 Oct 2016
 *
 */

public class ProductServiceImpl implements ProductService {

	@Inject
	@Log
	private Logger log;

	// Basic sales tax
	private static final BigDecimal BASIC_SALES_TAX = new BigDecimal("10");
	// Sales tax for import duty
	private static final BigDecimal IMPORT_SALES_TAX = new BigDecimal("5");
	// Scale to round up the sales tax
	private static final BigDecimal ROUND_SCALE = new BigDecimal("0.05");

	@Override
	public Product calculateProductSalesTax(Product product) throws TaxyException {
		try {
			log.debug("ProductServiceImpl::calculateProductSalesTax::start::product = {} ", product);

			if (!product.isImported() && product.getCategory().isTaxeable()) {
				product.setTaxedPrice(product.getPrice().add(calculateProductBasicSalesTax(product.getPrice())));
			} else if (product.isImported() && product.getCategory().isTaxeable()) {
				product.setTaxedPrice(product.getPrice().add(
					calculateProductBasicSalesTax(product.getPrice()).add(
						calculateProductImportSalesTax(product.getPrice()))));
			} else if (product.isImported() && !product.getCategory().isTaxeable()) {
				product.setTaxedPrice(product.getPrice().add(calculateProductImportSalesTax(product.getPrice())));
			} else {
				product.setTaxedPrice(product.getPrice());
			}

			log.debug("InvoiceService::calculateProductSalesTax::end::product = {} ", product);
			return product;
		} catch (Exception e) {
			throw new TaxyException("calculateProductSalesTax", e);
		}
	}

	protected BigDecimal calculateProductBasicSalesTax(BigDecimal price) {
		return roundSaleTax(price.divide(new BigDecimal(100)).multiply(BASIC_SALES_TAX));
	}

	protected BigDecimal calculateProductImportSalesTax(BigDecimal price) {
		return roundSaleTax(price.divide(new BigDecimal(100)).multiply(IMPORT_SALES_TAX));
	}

	protected BigDecimal roundSaleTax(BigDecimal price) {
		return price.divide(ROUND_SCALE).setScale(0, BigDecimal.ROUND_UP).multiply(ROUND_SCALE);
	}
}
