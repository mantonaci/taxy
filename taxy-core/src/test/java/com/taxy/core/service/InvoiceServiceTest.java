/*
 * @(#)InvoiceServiceTest.java        1.00	8 Oct 2016
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
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import com.taxy.core.annotation.Log;
import com.taxy.core.model.Product;
import com.taxy.core.model.enumeration.ProductCategory;
import com.taxy.core.runner.WeldJUnit4Runner;

/**
 * Class <code>InvoiceServiceTest.java</code> is
 *
 * @author Michele Antonaci antonaci.michele@gmail.com
 * @version 1.00 8 Oct 2016
 *
 */

@RunWith(WeldJUnit4Runner.class)  
public class InvoiceServiceTest {

	@Inject @Log
	private Logger LOG;
	
	@Inject
	InvoiceService invoiceService;

	List<Product> shoppingBaskets1 = new ArrayList<>();
	List<Product> shoppingBaskets2 = new ArrayList<>();
	List<Product> shoppingBaskets3 = new ArrayList<>();

	@Before
	public void init() {

		// SHOPPING BASKETS 1
		shoppingBaskets1.add(new Product("Crypto", new BigDecimal("12.49"), new BigDecimal("0"), ProductCategory.BOOK, false));
		shoppingBaskets1.add(new Product("Mumford & Sons CD", new BigDecimal("14.99"), new BigDecimal("0"), ProductCategory.MUSIC, false));
		shoppingBaskets1.add(new Product("Ferrero chocolate", new BigDecimal("0.85"), new BigDecimal("0"), ProductCategory.FOOD, false));

		// SHOPPING BASKETS 2
		shoppingBaskets2.add(new Product("Perugina box chocolates", new BigDecimal("10.00"), new BigDecimal("0"), ProductCategory.FOOD, true));
		shoppingBaskets2.add(new Product("One million", new BigDecimal("47.50"), new BigDecimal("0"), ProductCategory.PERFUME, true));

		// SHOPPING BASKETS 3
		shoppingBaskets3.add(new Product("D&G", new BigDecimal("27.99"), new BigDecimal("0"), ProductCategory.PERFUME, true));
		shoppingBaskets3.add(new Product("Ugo Boss", new BigDecimal("18.99"), new BigDecimal("0"), ProductCategory.PERFUME, false));
		shoppingBaskets3.add(new Product("Aulin", new BigDecimal("9.75"), new BigDecimal("0"), ProductCategory.MEDICAL, false));
		shoppingBaskets3.add(new Product("Novi chocolate", new BigDecimal("11.25"), new BigDecimal("0"), ProductCategory.FOOD, true));
	}

	@Test
	public void calculateInvoiceTest() {

		// Calculate invoice for shopping baskets 1
		Assert.assertTrue("Shopping Baskets 1 is empty", !shoppingBaskets1.isEmpty());
		LOG.info("SHOPPING BASKET 1 :: {}", invoiceService.calculateInvoice(shoppingBaskets1));

		// Calculate invoice for shopping baskets 2
		Assert.assertTrue("Shopping Baskets 2 is empty", !shoppingBaskets2.isEmpty());
		LOG.info("SHOPPING BASKET 2 :: {}", invoiceService.calculateInvoice(shoppingBaskets2));

		// Calculate invoice for shopping baskets 3
		Assert.assertTrue("Shopping Baskets 3 is empty", !shoppingBaskets3.isEmpty());
		LOG.info("SHOPPING BASKET 3 :: {}", invoiceService.calculateInvoice(shoppingBaskets3));
	}

	@Test
	public void calculateProductBasicSalesTaxTest() {
		// Basic sales tax fixed at 10%
		Assert.assertEquals(new BigDecimal("10.00"), invoiceService.calculateProductBasicSalesTax(new BigDecimal("100.00")));
	}

	@Test
	public void calculateProductImportSalesTaxTest() {
		// Imported sales tax fixed at 5%
		Assert.assertEquals(new BigDecimal("5.00"), invoiceService.calculateProductImportSalesTax(new BigDecimal("100.00")));
	}
}
