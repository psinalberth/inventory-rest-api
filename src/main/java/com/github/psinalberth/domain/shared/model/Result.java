package com.github.psinalberth.domain.shared.model;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Result<V> {

    public static <V> Result<V> fromSupplier(final Supplier<V> supplier) {
        try {
            return new Success<>(supplier.get());
        } catch (Throwable ex) {
            return new Failure<>(ex);
        }
    }

    public static <V> Result<V> fromFuture(final Supplier<CompletableFuture<? extends V>> futureSupplier) {
        try {
            return new Success<>(futureSupplier.get().join());
        } catch (Throwable ex) {
            return new Failure<>(ex);
        }
    }

    public static <V> Result<V> success(final V value) {
        return new Success<>(value);
    }

    public static <V> Result<V> of(Class<V> type) {
        return new Success<>(type.cast(null));
    }

    public static <V> Result<V> failure(final Throwable exception) {
        return new Failure<>(exception);
    }

    public abstract V getValue();

    public abstract Throwable getException();

    public abstract boolean isSuccess();

    public abstract boolean isFailure();

    public boolean isEmpty() {
        return Objects.isNull(this.getValue());
    }

    public Result<V> onEmptyFailWith(final Supplier<Throwable> exceptionSupplier) {

        if (this.isFailure())
            return this;

        return this.isEmpty() ? new Failure<>(exceptionSupplier.get()) : this;
    }

    public Result<V> onEmptyTransform(final Supplier<CompletableFuture<? extends V>> futureSupplier) {
        return this.isEmpty() ? fromFuture(futureSupplier) : this;
    }

    public Result<V> onEmptySwitchTo(final Supplier<Result<V>> futureSupplier) {
        return this.isEmpty() ? futureSupplier.get() : this;
    }

    @SuppressWarnings("unchecked")
    public <U> Result<U> map(final Function<? super V, ? extends U> mapper) {

        if (this.isSuccess() && !this.isEmpty())
            return fromSupplier(() -> mapper.apply(this.getValue()));

        return (Result<U>) this;
    }

    @SuppressWarnings("unchecked")
    public <U> Result<U> flatMap(final Function<? super V, ? extends Result<? extends U>> mapper) {
        if (this.isSuccess() && !this.isEmpty())
            return (Result<U>) mapper.apply(this.getValue());

        return (Result<U>) this;
    }

    public CompletableFuture<Result<V>> toFuture() {
        return CompletableFuture.supplyAsync(() -> this);
    }

    public Result<V> fail(final Supplier<Throwable> exceptionSupplier) {
        return failure(exceptionSupplier.get());
    }

    private static class Success<V> extends Result<V> {

        private final V value;

        private Success(V value) {
            this.value = value;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public Throwable getException() {
            return null;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public boolean isFailure() {
            return false;
        }
    }

    private static class Failure<E> extends Result<E> {

        private final Throwable exception;

        private Failure(Throwable exception) {
            this.exception = exception;
        }

        @Override
        public E getValue() {
            return null;
        }

        @Override
        public Throwable getException() {
            return exception;
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public boolean isFailure() {
            return true;
        }
    }
}
