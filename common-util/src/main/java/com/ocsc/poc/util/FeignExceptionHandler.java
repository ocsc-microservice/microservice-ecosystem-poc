package com.ocsc.poc.util;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignExceptionHandler  implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 404: {
                return new RecordNotFoundException(response.reason());
            }
            default:{
                return new Exception(response.reason());
            }
        }
    }
}
