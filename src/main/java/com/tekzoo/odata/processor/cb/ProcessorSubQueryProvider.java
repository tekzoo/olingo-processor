package com.tekzoo.odata.processor.cb;

public interface ProcessorSubQueryProvider {
  public <U> ProcessorSubquery<U> subquery(Class<U> type);
}
