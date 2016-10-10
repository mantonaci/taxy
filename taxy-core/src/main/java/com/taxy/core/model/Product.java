/*
 * @(#)Item.java        1.00	7 Oct 2016
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

package com.taxy.core.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.taxy.core.enumeration.ProductCategory;

/**
 * Class <code>Item.java</code> is
 *
 * @author Michele Antonaci antonaci.michele@gmail.com
 * @version 1.00 7 Oct 2016
 *
 */

public class Product {

	private String title;
	@NotNull
	private BigDecimal price;
	private BigDecimal taxedPrice;
	@NotNull
	private ProductCategory category;
	@NotNull
	private boolean imported;

	public Product() {
	}

	public Product(String title, BigDecimal price, BigDecimal taxedPrice, ProductCategory category, boolean imported) {
		this.title = title;
		this.price = price;
		this.taxedPrice = taxedPrice;
		this.category = category;
		this.imported = imported;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTaxedPrice() {
		return taxedPrice;
	}

	public void setTaxedPrice(BigDecimal taxedPrice) {
		this.taxedPrice = taxedPrice;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [title=");
		builder.append(title);
		builder.append(", price=");
		builder.append(price);
		builder.append(", taxedPrice=");
		builder.append(taxedPrice);
		builder.append(", category=");
		builder.append(category);
		builder.append(", imported=");
		builder.append(imported);
		builder.append("]");
		return builder.toString();
	}
	
}
