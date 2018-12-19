
package enn.monitor.trace.proto.model.trace.ennzipkin2.codec;

import enn.monitor.trace.proto.model.trace.ennzipkin2.internal.Nullable;

import java.util.Collection;
import java.util.List;

/**
 * This type accepts a collection that receives decoded elements.
 *
 */
// This receives a collection, not a function because it profiles 10% faster and all use cases are
// collections. This receives collection as opposed to list as zipkin-dependencies decodes into a
// set to dedupe redundant messages.
public interface BytesDecoder<T> {
  Encoding encoding();

  /**
   * This is used seldomly as the canonical message form is a {link #decodeList(byte[], Collection)
   * list}.
   *
   * Note: multiple elements can be consumed from a single serialized object. For example, if the
   * input is Zipkin v1, the list might receive two elements if the serialized object was a shared
   * span.
   *
   * param serialized a single message, for example a json object
   * return true if an element was decoded
   * throws {linkplain IllegalArgumentException} if the type couldn't be decoded
   */
  // used by zipkin-dependencies which reads elements one-at-a-time from ES documents
  boolean decode(byte[] serialized, Collection<T> out);

  /** Visible for testing. This returns the first element parsed from the serialized object or null */
  @Nullable
  T decodeOne(byte[] serialized);

  /**
   * return true if an element was decoded
   * throws {linkplain IllegalArgumentException} if the type couldn't be decoded
   */
  boolean decodeList(byte[] serialized, Collection<T> out);

  /** Convenience method for {link #decode(byte[], Collection)} */
  List<T> decodeList(byte[] serialized);
}
