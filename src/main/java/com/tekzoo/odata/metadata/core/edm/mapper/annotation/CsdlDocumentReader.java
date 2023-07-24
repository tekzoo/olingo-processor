package com.tekzoo.odata.metadata.core.edm.mapper.annotation;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.tekzoo.odata.metadata.core.edm.mapper.exception.ODataJPAModelException;
import com.tekzoo.odata.metadata.core.edm.mapper.exception.ODataJPAModelException.MessageKeys;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author Oliver Grande
 * @since 15.12.2019
 * @version 0.3.8
 *
 */
public class CsdlDocumentReader {
  final JacksonXmlModule module;
  final XmlMapper xmlMapper;

  /**
   *
   */
  public CsdlDocumentReader() {
    super();
    module = new JacksonXmlModule();
    module.setDefaultUseWrapper(false);
    xmlMapper = new XmlMapper(module);
  }

  String loadXML(@Nonnull final String path, @Nonnull final Charset charset) throws IOException,
          ODataJPAModelException {

    final Optional<InputStream> reader = Optional.ofNullable(this.getClass().getClassLoader().getResourceAsStream(path));

    final StringBuilder content = new StringBuilder();

    try (
            final InputStreamReader input = new InputStreamReader(reader.orElseThrow(
                    () -> new ODataJPAModelException(MessageKeys.FILE_NOT_FOUND, path)), charset);
            final BufferedReader br = new BufferedReader(input)) {
      String line;
      while ((line = br.readLine()) != null) {
        content.append(line);
      }
    }
    return content.toString();
  }

  /**
   *
   * @param path
   * @param charset
   * @return
   * @throws IOException
   * @throws ODataJPAModelException
   * @throws NullPointerException
   */
  public CsdlDocument readFromResource(@Nonnull final String path, @Nonnull final Charset charset)
          throws IOException, ODataJPAModelException {

    if (Objects.requireNonNull(path).isEmpty())
      return null;
    return xmlMapper.readValue(loadXML(path, Objects.requireNonNull(charset)),
            CsdlDocument.class);
  }

  /**
   *
   * @param uri
   * @return
   * @throws IOException
   * @throws NullPointerException
   */
  public CsdlDocument readFromURI(@Nonnull final URI uri) throws IOException {

    return xmlMapper.readValue(Objects.requireNonNull(uri).toURL(), CsdlDocument.class);
  }
}