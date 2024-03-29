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
@org.apache.avro.specific.AvroGenerated
public class HoodieActionInstant extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 8235099814567308118L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieActionInstant\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"timestamp\",\"type\":\"string\"},{\"name\":\"action\",\"type\":\"string\"},{\"name\":\"state\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<HoodieActionInstant> ENCODER =
      new BinaryMessageEncoder<HoodieActionInstant>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<HoodieActionInstant> DECODER =
      new BinaryMessageDecoder<HoodieActionInstant>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<HoodieActionInstant> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<HoodieActionInstant> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<HoodieActionInstant>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this HoodieActionInstant to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a HoodieActionInstant from a ByteBuffer. */
  public static HoodieActionInstant fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence timestamp;
  @Deprecated public java.lang.CharSequence action;
  @Deprecated public java.lang.CharSequence state;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public HoodieActionInstant() {}

  /**
   * All-args constructor.
   * @param timestamp The new value for timestamp
   * @param action The new value for action
   * @param state The new value for state
   */
  public HoodieActionInstant(java.lang.CharSequence timestamp, java.lang.CharSequence action, java.lang.CharSequence state) {
    this.timestamp = timestamp;
    this.action = action;
    this.state = state;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return timestamp;
    case 1: return action;
    case 2: return state;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: timestamp = (java.lang.CharSequence)value$; break;
    case 1: action = (java.lang.CharSequence)value$; break;
    case 2: state = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'timestamp' field.
   * @return The value of the 'timestamp' field.
   */
  public java.lang.CharSequence getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the value of the 'timestamp' field.
   * @param value the value to set.
   */
  public void setTimestamp(java.lang.CharSequence value) {
    this.timestamp = value;
  }

  /**
   * Gets the value of the 'action' field.
   * @return The value of the 'action' field.
   */
  public java.lang.CharSequence getAction() {
    return action;
  }

  /**
   * Sets the value of the 'action' field.
   * @param value the value to set.
   */
  public void setAction(java.lang.CharSequence value) {
    this.action = value;
  }

  /**
   * Gets the value of the 'state' field.
   * @return The value of the 'state' field.
   */
  public java.lang.CharSequence getState() {
    return state;
  }

  /**
   * Sets the value of the 'state' field.
   * @param value the value to set.
   */
  public void setState(java.lang.CharSequence value) {
    this.state = value;
  }

  /**
   * Creates a new HoodieActionInstant RecordBuilder.
   * @return A new HoodieActionInstant RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieActionInstant.Builder newBuilder() {
    return new org.apache.hudi.avro.model.HoodieActionInstant.Builder();
  }

  /**
   * Creates a new HoodieActionInstant RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new HoodieActionInstant RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieActionInstant.Builder newBuilder(org.apache.hudi.avro.model.HoodieActionInstant.Builder other) {
    return new org.apache.hudi.avro.model.HoodieActionInstant.Builder(other);
  }

  /**
   * Creates a new HoodieActionInstant RecordBuilder by copying an existing HoodieActionInstant instance.
   * @param other The existing instance to copy.
   * @return A new HoodieActionInstant RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieActionInstant.Builder newBuilder(org.apache.hudi.avro.model.HoodieActionInstant other) {
    return new org.apache.hudi.avro.model.HoodieActionInstant.Builder(other);
  }

  /**
   * RecordBuilder for HoodieActionInstant instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<HoodieActionInstant>
    implements org.apache.avro.data.RecordBuilder<HoodieActionInstant> {

    private java.lang.CharSequence timestamp;
    private java.lang.CharSequence action;
    private java.lang.CharSequence state;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieActionInstant.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[0].schema(), other.timestamp);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.action)) {
        this.action = data().deepCopy(fields()[1].schema(), other.action);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.state)) {
        this.state = data().deepCopy(fields()[2].schema(), other.state);
        fieldSetFlags()[2] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing HoodieActionInstant instance
     * @param other The existing instance to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieActionInstant other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[0].schema(), other.timestamp);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.action)) {
        this.action = data().deepCopy(fields()[1].schema(), other.action);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.state)) {
        this.state = data().deepCopy(fields()[2].schema(), other.state);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'timestamp' field.
      * @return The value.
      */
    public java.lang.CharSequence getTimestamp() {
      return timestamp;
    }

    /**
      * Sets the value of the 'timestamp' field.
      * @param value The value of 'timestamp'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieActionInstant.Builder setTimestamp(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.timestamp = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'timestamp' field has been set.
      * @return True if the 'timestamp' field has been set, false otherwise.
      */
    public boolean hasTimestamp() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'timestamp' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieActionInstant.Builder clearTimestamp() {
      timestamp = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'action' field.
      * @return The value.
      */
    public java.lang.CharSequence getAction() {
      return action;
    }

    /**
      * Sets the value of the 'action' field.
      * @param value The value of 'action'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieActionInstant.Builder setAction(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.action = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'action' field has been set.
      * @return True if the 'action' field has been set, false otherwise.
      */
    public boolean hasAction() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'action' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieActionInstant.Builder clearAction() {
      action = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'state' field.
      * @return The value.
      */
    public java.lang.CharSequence getState() {
      return state;
    }

    /**
      * Sets the value of the 'state' field.
      * @param value The value of 'state'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieActionInstant.Builder setState(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.state = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'state' field has been set.
      * @return True if the 'state' field has been set, false otherwise.
      */
    public boolean hasState() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'state' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieActionInstant.Builder clearState() {
      state = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HoodieActionInstant build() {
      try {
        HoodieActionInstant record = new HoodieActionInstant();
        record.timestamp = fieldSetFlags()[0] ? this.timestamp : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.action = fieldSetFlags()[1] ? this.action : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.state = fieldSetFlags()[2] ? this.state : (java.lang.CharSequence) defaultValue(fields()[2]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<HoodieActionInstant>
    WRITER$ = (org.apache.avro.io.DatumWriter<HoodieActionInstant>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<HoodieActionInstant>
    READER$ = (org.apache.avro.io.DatumReader<HoodieActionInstant>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
