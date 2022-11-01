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
public class HoodieCommitMetadata extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -621377441954715238L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieCommitMetadata\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"partitionToWriteStats\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieWriteStat\",\"fields\":[{\"name\":\"fileId\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"path\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"prevCommit\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"numWrites\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numDeletes\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numUpdateWrites\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalWriteBytes\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalWriteErrors\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"partitionPath\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"totalLogRecords\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogFiles\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalUpdatedRecordsCompacted\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numInserts\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogBlocks\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalCorruptLogBlock\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalRollbackBlocks\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"fileSizeInBytes\",\"type\":[\"null\",\"long\"],\"default\":null}]}}}],\"default\":null},{\"name\":\"extraMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"string\",\"default\":null}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"operationType\",\"type\":[\"null\",\"string\"],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<HoodieCommitMetadata> ENCODER =
      new BinaryMessageEncoder<HoodieCommitMetadata>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<HoodieCommitMetadata> DECODER =
      new BinaryMessageDecoder<HoodieCommitMetadata>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<HoodieCommitMetadata> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<HoodieCommitMetadata> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<HoodieCommitMetadata>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this HoodieCommitMetadata to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a HoodieCommitMetadata from a ByteBuffer. */
  public static HoodieCommitMetadata fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieWriteStat>> partitionToWriteStats;
  @Deprecated public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> extraMetadata;
  @Deprecated public java.lang.Integer version;
  @Deprecated public java.lang.CharSequence operationType;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public HoodieCommitMetadata() {}

  /**
   * All-args constructor.
   * @param partitionToWriteStats The new value for partitionToWriteStats
   * @param extraMetadata The new value for extraMetadata
   * @param version The new value for version
   * @param operationType The new value for operationType
   */
  public HoodieCommitMetadata(java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieWriteStat>> partitionToWriteStats, java.util.Map<java.lang.CharSequence,java.lang.CharSequence> extraMetadata, java.lang.Integer version, java.lang.CharSequence operationType) {
    this.partitionToWriteStats = partitionToWriteStats;
    this.extraMetadata = extraMetadata;
    this.version = version;
    this.operationType = operationType;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return partitionToWriteStats;
    case 1: return extraMetadata;
    case 2: return version;
    case 3: return operationType;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: partitionToWriteStats = (java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieWriteStat>>)value$; break;
    case 1: extraMetadata = (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>)value$; break;
    case 2: version = (java.lang.Integer)value$; break;
    case 3: operationType = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'partitionToWriteStats' field.
   * @return The value of the 'partitionToWriteStats' field.
   */
  public java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieWriteStat>> getPartitionToWriteStats() {
    return partitionToWriteStats;
  }

  /**
   * Sets the value of the 'partitionToWriteStats' field.
   * @param value the value to set.
   */
  public void setPartitionToWriteStats(java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieWriteStat>> value) {
    this.partitionToWriteStats = value;
  }

  /**
   * Gets the value of the 'extraMetadata' field.
   * @return The value of the 'extraMetadata' field.
   */
  public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getExtraMetadata() {
    return extraMetadata;
  }

  /**
   * Sets the value of the 'extraMetadata' field.
   * @param value the value to set.
   */
  public void setExtraMetadata(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
    this.extraMetadata = value;
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
   * Gets the value of the 'operationType' field.
   * @return The value of the 'operationType' field.
   */
  public java.lang.CharSequence getOperationType() {
    return operationType;
  }

  /**
   * Sets the value of the 'operationType' field.
   * @param value the value to set.
   */
  public void setOperationType(java.lang.CharSequence value) {
    this.operationType = value;
  }

  /**
   * Creates a new HoodieCommitMetadata RecordBuilder.
   * @return A new HoodieCommitMetadata RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieCommitMetadata.Builder newBuilder() {
    return new org.apache.hudi.avro.model.HoodieCommitMetadata.Builder();
  }

  /**
   * Creates a new HoodieCommitMetadata RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new HoodieCommitMetadata RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieCommitMetadata.Builder newBuilder(org.apache.hudi.avro.model.HoodieCommitMetadata.Builder other) {
    return new org.apache.hudi.avro.model.HoodieCommitMetadata.Builder(other);
  }

  /**
   * Creates a new HoodieCommitMetadata RecordBuilder by copying an existing HoodieCommitMetadata instance.
   * @param other The existing instance to copy.
   * @return A new HoodieCommitMetadata RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieCommitMetadata.Builder newBuilder(org.apache.hudi.avro.model.HoodieCommitMetadata other) {
    return new org.apache.hudi.avro.model.HoodieCommitMetadata.Builder(other);
  }

  /**
   * RecordBuilder for HoodieCommitMetadata instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<HoodieCommitMetadata>
    implements org.apache.avro.data.RecordBuilder<HoodieCommitMetadata> {

    private java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieWriteStat>> partitionToWriteStats;
    private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> extraMetadata;
    private java.lang.Integer version;
    private java.lang.CharSequence operationType;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieCommitMetadata.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.partitionToWriteStats)) {
        this.partitionToWriteStats = data().deepCopy(fields()[0].schema(), other.partitionToWriteStats);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.extraMetadata)) {
        this.extraMetadata = data().deepCopy(fields()[1].schema(), other.extraMetadata);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.version)) {
        this.version = data().deepCopy(fields()[2].schema(), other.version);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.operationType)) {
        this.operationType = data().deepCopy(fields()[3].schema(), other.operationType);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing HoodieCommitMetadata instance
     * @param other The existing instance to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieCommitMetadata other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.partitionToWriteStats)) {
        this.partitionToWriteStats = data().deepCopy(fields()[0].schema(), other.partitionToWriteStats);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.extraMetadata)) {
        this.extraMetadata = data().deepCopy(fields()[1].schema(), other.extraMetadata);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.version)) {
        this.version = data().deepCopy(fields()[2].schema(), other.version);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.operationType)) {
        this.operationType = data().deepCopy(fields()[3].schema(), other.operationType);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'partitionToWriteStats' field.
      * @return The value.
      */
    public java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieWriteStat>> getPartitionToWriteStats() {
      return partitionToWriteStats;
    }

    /**
      * Sets the value of the 'partitionToWriteStats' field.
      * @param value The value of 'partitionToWriteStats'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCommitMetadata.Builder setPartitionToWriteStats(java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieWriteStat>> value) {
      validate(fields()[0], value);
      this.partitionToWriteStats = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'partitionToWriteStats' field has been set.
      * @return True if the 'partitionToWriteStats' field has been set, false otherwise.
      */
    public boolean hasPartitionToWriteStats() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'partitionToWriteStats' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCommitMetadata.Builder clearPartitionToWriteStats() {
      partitionToWriteStats = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'extraMetadata' field.
      * @return The value.
      */
    public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getExtraMetadata() {
      return extraMetadata;
    }

    /**
      * Sets the value of the 'extraMetadata' field.
      * @param value The value of 'extraMetadata'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCommitMetadata.Builder setExtraMetadata(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
      validate(fields()[1], value);
      this.extraMetadata = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'extraMetadata' field has been set.
      * @return True if the 'extraMetadata' field has been set, false otherwise.
      */
    public boolean hasExtraMetadata() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'extraMetadata' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCommitMetadata.Builder clearExtraMetadata() {
      extraMetadata = null;
      fieldSetFlags()[1] = false;
      return this;
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
    public org.apache.hudi.avro.model.HoodieCommitMetadata.Builder setVersion(java.lang.Integer value) {
      validate(fields()[2], value);
      this.version = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'version' field has been set.
      * @return True if the 'version' field has been set, false otherwise.
      */
    public boolean hasVersion() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'version' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCommitMetadata.Builder clearVersion() {
      version = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'operationType' field.
      * @return The value.
      */
    public java.lang.CharSequence getOperationType() {
      return operationType;
    }

    /**
      * Sets the value of the 'operationType' field.
      * @param value The value of 'operationType'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCommitMetadata.Builder setOperationType(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.operationType = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'operationType' field has been set.
      * @return True if the 'operationType' field has been set, false otherwise.
      */
    public boolean hasOperationType() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'operationType' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCommitMetadata.Builder clearOperationType() {
      operationType = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HoodieCommitMetadata build() {
      try {
        HoodieCommitMetadata record = new HoodieCommitMetadata();
        record.partitionToWriteStats = fieldSetFlags()[0] ? this.partitionToWriteStats : (java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieWriteStat>>) defaultValue(fields()[0]);
        record.extraMetadata = fieldSetFlags()[1] ? this.extraMetadata : (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>) defaultValue(fields()[1]);
        record.version = fieldSetFlags()[2] ? this.version : (java.lang.Integer) defaultValue(fields()[2]);
        record.operationType = fieldSetFlags()[3] ? this.operationType : (java.lang.CharSequence) defaultValue(fields()[3]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<HoodieCommitMetadata>
    WRITER$ = (org.apache.avro.io.DatumWriter<HoodieCommitMetadata>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<HoodieCommitMetadata>
    READER$ = (org.apache.avro.io.DatumReader<HoodieCommitMetadata>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
