/*
 * @(#)Product.java        1.00	7 Oct 2016
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

package com.taxy.core.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.taxy.core.model.enumeration.ProductCategory;

/**
 * Class <code>Product.java</code> is
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
	private Boolean imported;

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

	public Boolean isImported() {
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
