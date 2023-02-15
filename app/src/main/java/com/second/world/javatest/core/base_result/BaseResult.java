package com.second.world.javatest.core.base_result;

public interface BaseResult<T>{

    Integer getCode();

    default T getData() {
        return null;
    }

    class Success<T> implements BaseResult<T> {

        private final T data;

        public Success(T data) {
            this.data = data;
        }

        @Override
        public Integer getCode() {
            return ResultCode.OK.getCode();
        }

        @Override
        public T getData() {
            return data;
        }
    }

    class Error<T> implements BaseResult<T>{

        private final T message;

        public Error(T message) {
            this.message = message;
        }

        @Override
        public Integer getCode() {
            return ResultCode.ERROR.getCode();
        }

        @Override
        public T getData() {
            return message;
        }
    }

    class Loading<T> implements BaseResult<T>{

        public Loading() {}

        @Override
        public Integer getCode() {
            return ResultCode.LOADING.getCode();
        }
    }
}
