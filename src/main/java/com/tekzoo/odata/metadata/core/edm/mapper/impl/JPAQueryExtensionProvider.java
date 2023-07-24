package com.tekzoo.odata.metadata.core.edm.mapper.impl;

import static com.tekzoo.odata.metadata.core.edm.mapper.exception.ODataJPAModelException.MessageKeys.EXTENSION_PROVIDER_TOO_MANY_CONSTRUCTORS;
import static com.tekzoo.odata.metadata.core.edm.mapper.exception.ODataJPAModelException.MessageKeys.EXTENSION_PROVIDER_WRONG_PARAMETER;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

import javax.annotation.Nonnull;

import com.tekzoo.odata.metadata.api.JPAHttpHeaderMap;
import com.tekzoo.odata.metadata.api.JPARequestParameterMap;
import com.tekzoo.odata.metadata.core.edm.annotation.EdmQueryExtensionProvider;
import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAQueryExtension;
import com.tekzoo.odata.metadata.core.edm.mapper.exception.ODataJPAModelException;

class JPAQueryExtensionProvider<X extends EdmQueryExtensionProvider> implements JPAQueryExtension<X> {

  private final Constructor<X> constructor;

  @SuppressWarnings("unchecked")
  JPAQueryExtensionProvider(@Nonnull final Class<X> provider)
      throws ODataJPAModelException {

    final Constructor<?>[] constructors = provider.getConstructors();
    if (constructors.length > 1)
      throw new ODataJPAModelException(EXTENSION_PROVIDER_TOO_MANY_CONSTRUCTORS, provider.getCanonicalName());
    final Constructor<?> c = provider.getConstructors()[0];
    for (final Parameter p : c.getParameters()) {
      if (p.getType() != JPAHttpHeaderMap.class
          && p.getType() != JPARequestParameterMap.class)
        throw new ODataJPAModelException(EXTENSION_PROVIDER_WRONG_PARAMETER, provider.getCanonicalName());
    }
    this.constructor = (Constructor<X>) provider.getConstructors()[0];
  }

  @Override
  public Constructor<X> getConstructor() {
    return constructor;
  }

}
