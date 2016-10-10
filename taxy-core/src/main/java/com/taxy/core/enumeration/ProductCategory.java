/*
 * @(#)ProductCategory.java        1.00	7 Oct 2016
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

package com.taxy.core.enumeration;

/**
 * Class <code>ProductCategory.java</code> is
 *
 * @author Michele Antonaci antonaci.michele@gmail.com
 * @version 1.00 7 Oct 2016
 *
 */

public enum ProductCategory {

	BOOK(false), FOOD(false), MEDICAL(false), MUSIC(true), PERFUME(true);

	private final boolean taxeable;

	ProductCategory(boolean taxeable) {
		this.taxeable = taxeable;
	}

	public boolean isTaxeable() {
		return this.taxeable;
	}

}
