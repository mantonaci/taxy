/*
 * @(#)Test.java        1.00	8 Oct 2016
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

import it.xpeppers.taxy.core.model.Product;
import it.xpeppers.taxy.core.service.impl.InvoiceServiceImpl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Class <code>Test.java</code> is
 *
 * @author Michele Antonaci antonaci.michele@gmail.com
 * @version 1.00 8 Oct 2016
 *
 */

@Produces("application/json")
@Consumes("application/json")
@Path("/api")
public class InvoiceRestService {

	InvoiceServiceImpl invoiceServiceImpl = new InvoiceServiceImpl();

	//FIXME: wrapper errors
	@POST
	@Path("/invoice")
	public Response printMessage(List<Product> products) {
		return Response.status(200).entity(invoiceServiceImpl.calculateInvoice(products)).build();
	}

}
