/*
 * @(#)InvoiceServiceTest.java        1.00	8 Oct 2016
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

package it.xpeppers.taxy.core.service;

import it.xpeppers.taxy.core.enumeration.ProductCategory;
import it.xpeppers.taxy.core.model.Product;
import it.xpeppers.taxy.core.runner.WeldJUnit4Runner;
import it.xpeppers.taxy.core.service.InvoiceService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class <code>InvoiceServiceTest.java</code> is
 *
 * @author Michele Antonaci antonaci.michele@gmail.com
 * @version 1.00 8 Oct 2016
 *
 */

@RunWith(WeldJUnit4Runner.class)  
public class InvoiceServiceImplTest {

	static final Logger LOG = LoggerFactory.getLogger(InvoiceServiceImplTest.class);

	@Inject
	InvoiceService invoiceService;// = new InvoiceServiceImpl();

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
