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
public class HoodieIndexPartitionInfo extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -2063629342880288203L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieIndexPartitionInfo\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"metadataPartitionPath\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"indexUptoInstant\",\"type\":[\"null\",\"string\"],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<HoodieIndexPartitionInfo> ENCODER =
      new BinaryMessageEncoder<HoodieIndexPartitionInfo>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<HoodieIndexPartitionInfo> DECODER =
      new BinaryMessageDecoder<HoodieIndexPartitionInfo>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<HoodieIndexPartitionInfo> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<HoodieIndexPartitionInfo> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<HoodieIndexPartitionInfo>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this HoodieIndexPartitionInfo to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a HoodieIndexPartitionInfo from a ByteBuffer. */
  public static HoodieIndexPartitionInfo fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.Integer version;
  @Deprecated public java.lang.CharSequence metadataPartitionPath;
  @Deprecated public java.lang.CharSequence indexUptoInstant;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public HoodieIndexPartitionInfo() {}

  /**
   * All-args constructor.
   * @param version The new value for version
   * @param metadataPartitionPath The new value for metadataPartitionPath
   * @param indexUptoInstant The new value for indexUptoInstant
   */
  public HoodieIndexPartitionInfo(java.lang.Integer version, java.lang.CharSequence metadataPartitionPath, java.lang.CharSequence indexUptoInstant) {
    this.version = version;
    this.metadataPartitionPath = metadataPartitionPath;
    this.indexUptoInstant = indexUptoInstant;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return version;
    case 1: return metadataPartitionPath;
    case 2: return indexUptoInstant;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: version = (java.lang.Integer)value$; break;
    case 1: metadataPartitionPath = (java.lang.CharSequence)value$; break;
    case 2: indexUptoInstant = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'version' field.
   * @return The value of the 'version' field.
   */
  public java.lang.Integer getVersion() {
    return version;
  }

  /**
   * Sets the value of the 'version' field.
   * @param value the value to set.
   */
  public void setVersion(java.lang.Integer value) {
    this.version = value;
  }

  /**
   * Gets the value of the 'metadataPartitionPath' field.
   * @return The value of the 'metadataPartitionPath' field.
   */
  public java.lang.CharSequence getMetadataPartitionPath() {
    return metadataPartitionPath;
  }

  /**
   * Sets the value of the 'metadataPartitionPath' field.
   * @param value the value to set.
   */
  public void setMetadataPartitionPath(java.lang.CharSequence value) {
    this.metadataPartitionPath = value;
  }

  /**
   * Gets the value of the 'indexUptoInstant' field.
   * @return The value of the 'indexUptoInstant' field.
   */
  public java.lang.CharSequence getIndexUptoInstant() {
    return indexUptoInstant;
  }

  /**
   * Sets the value of the 'indexUptoInstant' field.
   * @param value the value to set.
   */
  public void setIndexUptoInstant(java.lang.CharSequence value) {
    this.indexUptoInstant = value;
  }

  /**
   * Creates a new HoodieIndexPartitionInfo RecordBuilder.
   * @return A new HoodieIndexPartitionInfo RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieIndexPartitionInfo.Builder newBuilder() {
    return new org.apache.hudi.avro.model.HoodieIndexPartitionInfo.Builder();
  }

  /**
   * Creates a new HoodieIndexPartitionInfo RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new HoodieIndexPartitionInfo RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieIndexPartitionInfo.Builder newBuilder(org.apache.hudi.avro.model.HoodieIndexPartitionInfo.Builder other) {
    return new org.apache.hudi.avro.model.HoodieIndexPartitionInfo.Builder(other);
  }

  /**
   * Creates a new HoodieIndexPartitionInfo RecordBuilder by copying an existing HoodieIndexPartitionInfo instance.
   * @param other The existing instance to copy.
   * @return A new HoodieIndexPartitionInfo RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieIndexPartitionInfo.Builder newBuilder(org.apache.hudi.avro.model.HoodieIndexPartitionInfo other) {
    return new org.apache.hudi.avro.model.HoodieIndexPartitionInfo.Builder(other);
  }

  /**
   * RecordBuilder for HoodieIndexPartitionInfo instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<HoodieIndexPartitionInfo>
    implements org.apache.avro.data.RecordBuilder<HoodieIndexPartitionInfo> {

    private java.lang.Integer version;
    private java.lang.CharSequence metadataPartitionPath;
    private java.lang.CharSequence indexUptoInstant;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieIndexPartitionInfo.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.version)) {
        this.version = data().deepCopy(fields()[0].schema(), other.version);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.metadataPartitionPath)) {
        this.metadataPartitionPath = data().deepCopy(fields()[1].schema(), other.metadataPartitionPath);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.indexUptoInstant)) {
        this.indexUptoInstant = data().deepCopy(fields()[2].schema(), other.indexUptoInstant);
        fieldSetFlags()[2] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing HoodieIndexPartitionInfo instance
     * @param other The existing instance to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieIndexPartitionInfo other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.version)) {
        this.version = data().deepCopy(fields()[0].schema(), other.version);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.metadataPartitionPath)) {
        this.metadataPartitionPath = data().deepCopy(fields()[1].schema(), other.metadataPartitionPath);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.indexUptoInstant)) {
        this.indexUptoInstant = data().deepCopy(fields()[2].schema(), other.indexUptoInstant);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'version' field.
      * @return The value.
      */
    public java.lang.Integer getVersion() {
      return version;
    }

    /**
      * Sets the value of the 'version' field.
      * @param value The value of 'version'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieIndexPartitionInfo.Builder setVersion(java.lang.Integer value) {
      validate(fields()[0], value);
      this.version = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'version' field has been set.
      * @return True if the 'version' field has been set, false otherwise.
      */
    public boolean hasVersion() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'version' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieIndexPartitionInfo.Builder clearVersion() {
      version = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'metadataPartitionPath' field.
      * @return The value.
      */
    public java.lang.CharSequence getMetadataPartitionPath() {
      return metadataPartitionPath;
    }

    /**
      * Sets the value of the 'metadataPartitionPath' field.
      * @param value The value of 'metadataPartitionPath'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieIndexPartitionInfo.Builder setMetadataPartitionPath(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.metadataPartitionPath = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'metadataPartitionPath' field has been set.
      * @return True if the 'metadataPartitionPath' field has been set, false otherwise.
      */
    public boolean hasMetadataPartitionPath() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'metadataPartitionPath' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieIndexPartitionInfo.Builder clearMetadataPartitionPath() {
      metadataPartitionPath = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'indexUptoInstant' field.
      * @return The value.
      */
    public java.lang.CharSequence getIndexUptoInstant() {
      return indexUptoInstant;
    }

    /**
      * Sets the value of the 'indexUptoInstant' field.
      * @param value The value of 'indexUptoInstant'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieIndexPartitionInfo.Builder setIndexUptoInstant(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.indexUptoInstant = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'indexUptoInstant' field has been set.
      * @return True if the 'indexUptoInstant' field has been set, false otherwise.
      */
    public boolean hasIndexUptoInstant() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'indexUptoInstant' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieIndexPartitionInfo.Builder clearIndexUptoInstant() {
      indexUptoInstant = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HoodieIndexPartitionInfo build() {
      try {
        HoodieIndexPartitionInfo record = new HoodieIndexPartitionInfo();
        record.version = fieldSetFlags()[0] ? this.version : (java.lang.Integer) defaultValue(fields()[0]);
        record.metadataPartitionPath = fieldSetFlags()[1] ? this.metadataPartitionPath : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.indexUptoInstant = fieldSetFlags()[2] ? this.indexUptoInstant : (java.lang.CharSequence) defaultValue(fields()[2]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<HoodieIndexPartitionInfo>
    WRITER$ = (org.apache.avro.io.DatumWriter<HoodieIndexPartitionInfo>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<HoodieIndexPartitionInfo>
    READER$ = (org.apache.avro.io.DatumReader<HoodieIndexPartitionInfo>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
