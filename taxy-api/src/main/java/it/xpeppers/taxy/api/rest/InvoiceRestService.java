/*
 * @(#)InvoiceRestService.java        1.00	8 Oct 2016
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

package it.xpeppers.taxy.api.rest;

import it.xpeppers.taxy.core.model.Invoice;
import it.xpeppers.taxy.core.model.Product;
import it.xpeppers.taxy.core.service.InvoiceService;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
		return invoiceService.calculateInvoice(products); // FIXME: wrapper errors
	}

}
