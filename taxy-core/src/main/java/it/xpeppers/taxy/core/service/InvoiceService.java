/*
 * @(#)InvoiceService.java        1.00	7 Oct 2016
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

import it.xpeppers.taxy.core.model.Invoice;
import it.xpeppers.taxy.core.model.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class <code>InvoiceService.java</code> is
 *
 * @author Michele Antonaci antonaci.michele@gmail.com
 * @version 1.00 7 Oct 2016
 *
 */

public interface InvoiceService {

	/**
	 * Calculate invoice for shopping brackets
	 * @param products
	 * @return invoice with sales tax and total price {@link it.xpeppers.taxy.core.model.Invoice}
	 */
	public Invoice calculateInvoice(List<Product> products);
	
	/**
	 * Calculate fixed basic sales tax
	 * @param price without tax
	 * @return price calculated with basic sales tax
	 */
	public BigDecimal calculateProductBasicSalesTax(BigDecimal price);
	
	/**
	 * Calculate fixed import sales tax
	 * @param price without tax
	 * @return price calculated with basic sales tax
	 */
	public BigDecimal calculateProductImportSalesTax(BigDecimal price);
	
}
