/**
 * Copyright 2017, FMR LLC.
 * All Rights Reserved.
 * Fidelity Confidential Information
 */
package net.stn.streams;

/**
 * @author a365143
 *
 */
@FunctionalInterface
public interface FourArgumentFunction<T, P, Q, S, R> {

	R apply(T t, P p, Q q, S s);
}
