/*
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

package it.xpeppers.taxy.api.rest.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

/**
 * Class <code>TaxyApiExceptionHandler.java</code> is
 *
 * @author Michele Antonaci antonaci.michele@gmail.com
 * @version 1.00 9 Oct 2016
 *
 */

@Provider
public class UnrecognizedPropertyExceptionHandler implements ExceptionMapper<UnrecognizedPropertyException> {

	private static final Logger LOG = LoggerFactory.getLogger(UnrecognizedPropertyExceptionHandler.class);

	@Override
	public Response toResponse(UnrecognizedPropertyException unrecognizedPropertyException) {

		LOG.info("UnrecognizedPropertyExceptionHandler :: {}", unrecognizedPropertyException.getMessage());
		return Response.status(Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
	}
}
