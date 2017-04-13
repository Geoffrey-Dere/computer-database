package com.excilys.computerDatabase.model;

import java.util.ArrayList;
import java.util.List;

public class Pager<T> {

	private List<T> list_entity = new ArrayList<>();

	private Pager(BuilderPage<T> builder) {
		this.list_entity = builder.list;
	}

	public List<T> getList_entity() {
		return list_entity;
	}

	public static class BuilderPage<T> {
		private List<T> list;

		public BuilderPage(List<T> list) {
			this.list = list;

		}

		public Pager<T> build() {
			return new Pager<T>(this);
		}
	}
}
