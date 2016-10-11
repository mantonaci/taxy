/*
 * @(#)ProductServiceTest.java        1.00	11 Oct 2016
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

package com.taxy.core.service;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import com.taxy.core.annotation.Log;
import com.taxy.core.exception.TaxyException;
import com.taxy.core.model.Product;
import com.taxy.core.model.enumeration.ProductCategory;
import com.taxy.core.runner.WeldJUnit4Runner;

/**
 * Class <code>ProductServiceTest.java</code> is
 *
 * @author Michele Antonaci antonaci.michele@gmail.com
 * @version 1.00 11 Oct 2016
 *
 */

@RunWith(WeldJUnit4Runner.class)
public class ProductServiceTest {

	@Inject
	@Log
	private Logger log;

	@Inject
	ProductService productService;
	
	private Product product;

	@Before
	public void init() {
		product = new Product();
		product.setTitle("D&G");
		product.setCategory(ProductCategory.PERFUME);
		product.setPrice(new BigDecimal("10.00"));
		product.setImported(true);
	}

	@Test
	public void calculateProductSalesTaxTest() {
		try {
			productService.calculateProductSalesTax(product);
		} catch (TaxyException e) {
			log.error("calculateInvoiceTest", e);
		}
	}

}
