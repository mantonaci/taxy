/*
 * @(#)ValidationExceptionMapper.java        1.00	8 Oct 2016
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

package com.taxy.api.rest.exception;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.jboss.resteasy.api.validation.ResteasyViolationExceptionMapper;
import org.jboss.resteasy.api.validation.ViolationReport;
import org.slf4j.Logger;

import com.taxy.api.rest.violation.TaxyApiViolation;
import com.taxy.core.annotation.Log;

/**
 * Class <code>ValidationExceptionMapper.java</code> is
 *
 * @author Michele Antonaci antonaci.michele@gmail.com
 * @version 1.00 10 Oct 2016
 *
 */

@Provider
public class ValidationExceptionMapper extends ResteasyViolationExceptionMapper {

	@Inject
	@Log
	private Logger LOG;

	protected Response buildViolationReportResponse(ResteasyViolationException exception, Status status) {
		
		LOG.error("restapi:: status = {}, constraintViolationException :: {}", status.getStatusCode(), exception.getConstraintViolations());

		ViolationReport violationReport = new ViolationReport(exception);
		TaxyApiViolation taxyApiViolation = new TaxyApiViolation();
		taxyApiViolation.setCode(status.getStatusCode());
		taxyApiViolation.setMessage(violationReport.getParameterViolations());

		return Response.status(status).type(MediaType.APPLICATION_JSON).entity(taxyApiViolation).build();
	}
}
