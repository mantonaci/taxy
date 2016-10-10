/*
 * @(#)InvoiceRestService.java        1.00	8 Oct 2016
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

package com.taxy.api.rest;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.taxy.core.model.Invoice;
import com.taxy.core.model.Product;
import com.taxy.core.service.InvoiceService;

/**
 * Class <code>InvoiceRestService.java</code> is
 *
 * @author Michele Antonaci antonaci.michele@gmail.com
 * @version 1.00 8 Oct 2016
 *
 */

@Consumes("application/json")
@Produces("application/json")
@Path("/api")
public class InvoiceRestService {

	@Inject
	InvoiceService invoiceService;

	@POST
	@Path("/invoice")
	public Invoice create(@Valid List<Product> products) {
		return invoiceService.calculateInvoice(products);
	}

}
