package com.powerorg.powerplant.util;

import com.powerorg.powerplant.exception.APIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.HashSet;
import java.util.Set;

import static com.powerorg.powerplant.util.Constant.INVALID_REQUEST;

@ExtendWith(MockitoExtension.class)
public class ErrorHandlerTest {


    @Test
    public void test_api_exception_handler() {

        ErrorHandler errorHandler = new ErrorHandler();

        APIException apiException = new APIException(HttpStatus.BAD_REQUEST, INVALID_REQUEST, "Test Error");

        ResponseEntity<?> responseEntity = errorHandler.handleAPIException(apiException);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }


    @Test
    public void test_constraint_violation_exception_handler() {

        ErrorHandler errorHandler = new ErrorHandler();

        Validation.buildDefaultValidatorFactory().getValidator();


      ConstraintViolation<?> constraintViolation1 = new ConstraintViolation() {
          @Override
          public String getMessage() {
              return "testMessage1";
          }

          @Override
          public String getMessageTemplate() {
              return null;
          }

          @Override
          public Object getRootBean() {
              return null;
          }

          @Override
          public Class getRootBeanClass() {
              return null;
          }

          @Override
          public Object getLeafBean() {
              return null;
          }

          @Override
          public Object[] getExecutableParameters() {
              return new Object[0];
          }

          @Override
          public Object getExecutableReturnValue() {
              return null;
          }

          @Override
          public Path getPropertyPath() {
              return null;
          }

          @Override
          public Object getInvalidValue() {
              return null;
          }

          @Override
          public ConstraintDescriptor<?> getConstraintDescriptor() {
              return null;
          }

          @Override
          public Object unwrap(Class aClass) {
              return null;
          }
      };


        ConstraintViolation<?> constraintViolation2 = new ConstraintViolation() {
            @Override
            public String getMessage() {
                return "testMessage2";
            }

            @Override
            public String getMessageTemplate() {
                return null;
            }

            @Override
            public Object getRootBean() {
                return null;
            }

            @Override
            public Class getRootBeanClass() {
                return null;
            }

            @Override
            public Object getLeafBean() {
                return null;
            }

            @Override
            public Object[] getExecutableParameters() {
                return new Object[0];
            }

            @Override
            public Object getExecutableReturnValue() {
                return null;
            }

            @Override
            public Path getPropertyPath() {
                return null;
            }

            @Override
            public Object getInvalidValue() {
                return null;
            }

            @Override
            public ConstraintDescriptor<?> getConstraintDescriptor() {
                return null;
            }

            @Override
            public Object unwrap(Class aClass) {
                return null;
            }
        };

        Set<ConstraintViolation<?>> constraintViolations = new HashSet<>();

        constraintViolations.add(constraintViolation1);
        constraintViolations.add(constraintViolation2);

        ConstraintViolationException constraintViolationException = new ConstraintViolationException(constraintViolations);

        ResponseEntity<?> responseEntity = errorHandler.handleConstraintViolationException(constraintViolationException);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

}
