package com.max2.support;

public interface APIQueryOperation<Q,T> {
      T query(Q queryParams);
}
