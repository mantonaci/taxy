/*
 * @(#)ReceiptService.java        1.00	7 Oct 2016
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

package com.taxy.core.service;

import java.util.List;

import com.taxy.core.exception.TaxyException;
import com.taxy.core.model.Receipt;
import com.taxy.core.model.Product;

/**
 * Class <code>ReceiptService.java</code> is
 *
 * @author Michele Antonaci antonaci.michele@gmail.com
 * @version 1.00 7 Oct 2016
 *
 */

public interface ReceiptService {

	/**
	 * Calculate receipt for shopping brackets
	 * 
	 * @param products
	 * @return receipt with sales tax and total price
	 *         {@link com.taxy.core.model.Receipt}
	 */
	public Receipt calculateReceipt(List<Product> products) throws TaxyException;

}
