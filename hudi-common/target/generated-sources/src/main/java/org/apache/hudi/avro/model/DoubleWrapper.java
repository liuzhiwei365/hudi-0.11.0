/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.apache.hudi.avro.model;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
/** A record wrapping double type to be able to be used it w/in Avro's Union */
@org.apache.avro.specific.AvroGenerated
public class DoubleWrapper extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -6887538386354470690L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"DoubleWrapper\",\"namespace\":\"org.apache.hudi.avro.model\",\"doc\":\"A record wrapping double type to be able to be used it w/in Avro's Union\",\"fields\":[{\"name\":\"value\",\"type\":\"double\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<DoubleWrapper> ENCODER =
      new BinaryMessageEncoder<DoubleWrapper>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<DoubleWrapper> DECODER =
      new BinaryMessageDecoder<DoubleWrapper>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<DoubleWrapper> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<DoubleWrapper> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<DoubleWrapper>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this DoubleWrapper to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a DoubleWrapper from a ByteBuffer. */
  public static DoubleWrapper fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public double value;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public DoubleWrapper() {}

  /**
   * All-args constructor.
   * @param value The new value for value
   */
  public DoubleWrapper(java.lang.Double value) {
    this.value = value;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return value;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: value = (java.lang.Double)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'value' field.
   * @return The value of the 'value' field.
   */
  public java.lang.Double getValue() {
    return value;
  }

  /**
   * Sets the value of the 'value' field.
   * @param value the value to set.
   */
  public void setValue(java.lang.Double value) {
    this.value = value;
  }

  /**
   * Creates a new DoubleWrapper RecordBuilder.
   * @return A new DoubleWrapper RecordBuilder
   */
  public static org.apache.hudi.avro.model.DoubleWrapper.Builder newBuilder() {
    return new org.apache.hudi.avro.model.DoubleWrapper.Builder();
  }

  /**
   * Creates a new DoubleWrapper RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new DoubleWrapper RecordBuilder
   */
  public static org.apache.hudi.avro.model.DoubleWrapper.Builder newBuilder(org.apache.hudi.avro.model.DoubleWrapper.Builder other) {
    return new org.apache.hudi.avro.model.DoubleWrapper.Builder(other);
  }

  /**
   * Creates a new DoubleWrapper RecordBuilder by copying an existing DoubleWrapper instance.
   * @param other The existing instance to copy.
   * @return A new DoubleWrapper RecordBuilder
   */
  public static org.apache.hudi.avro.model.DoubleWrapper.Builder newBuilder(org.apache.hudi.avro.model.DoubleWrapper other) {
    return new org.apache.hudi.avro.model.DoubleWrapper.Builder(other);
  }

  /**
   * RecordBuilder for DoubleWrapper instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<DoubleWrapper>
    implements org.apache.avro.data.RecordBuilder<DoubleWrapper> {

    private double value;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.apache.hudi.avro.model.DoubleWrapper.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.value)) {
        this.value = data().deepCopy(fields()[0].schema(), other.value);
        fieldSetFlags()[0] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing DoubleWrapper instance
     * @param other The existing instance to copy.
     */
    private Builder(org.apache.hudi.avro.model.DoubleWrapper other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.value)) {
        this.value = data().deepCopy(fields()[0].schema(), other.value);
        fieldSetFlags()[0] = true;
      }
    }

    /**
      * Gets the value of the 'value' field.
      * @return The value.
      */
    public java.lang.Double getValue() {
      return value;
    }

    /**
      * Sets the value of the 'value' field.
      * @param value The value of 'value'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.DoubleWrapper.Builder setValue(double value) {
      validate(fields()[0], value);
      this.value = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'value' field has been set.
      * @return True if the 'value' field has been set, false otherwise.
      */
    public boolean hasValue() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'value' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.DoubleWrapper.Builder clearValue() {
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public DoubleWrapper build() {
      try {
        DoubleWrapper record = new DoubleWrapper();
        record.value = fieldSetFlags()[0] ? this.value : (java.lang.Double) defaultValue(fields()[0]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<DoubleWrapper>
    WRITER$ = (org.apache.avro.io.DatumWriter<DoubleWrapper>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<DoubleWrapper>
    READER$ = (org.apache.avro.io.DatumReader<DoubleWrapper>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
