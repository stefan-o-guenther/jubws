package de.htwaalen.jubws.utils;

public abstract class Predicate<T> {
	
	public abstract boolean apply(T obj);
	
	
	public Predicate<T> and(final Predicate<T> other){
		return new Predicate<T>() {
			@Override
			public boolean apply(T obj) {
				return this.apply(obj) && other.apply(obj);
			}
		};
	}
	
	public Predicate<T> or(final Predicate<T> other){
		return new Predicate<T>() {
			@Override
			public boolean apply(T obj) {
				return this.apply(obj) || other.apply(obj);
			}
		};
	}
	
	public Predicate<T> not(){
		return new Predicate<T>() {
			@Override
			public boolean apply(T obj) {
				return !this.apply(obj);
			}
		};
	}
}
