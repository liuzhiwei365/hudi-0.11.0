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
public class HoodieCompactionMetadata extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5395193606116331778L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieCompactionMetadata\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"partitionToCompactionWriteStats\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieCompactionWriteStat\",\"fields\":[{\"name\":\"partitionPath\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"totalLogRecords\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogFiles\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalUpdatedRecordsCompacted\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"hoodieWriteStat\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieWriteStat\",\"fields\":[{\"name\":\"fileId\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"path\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"prevCommit\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"numWrites\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numDeletes\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numUpdateWrites\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalWriteBytes\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalWriteErrors\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"partitionPath\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"totalLogRecords\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogFiles\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalUpdatedRecordsCompacted\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numInserts\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogBlocks\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalCorruptLogBlock\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalRollbackBlocks\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"fileSizeInBytes\",\"type\":[\"null\",\"long\"],\"default\":null}]}],\"default\":null}]}}}]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<HoodieCompactionMetadata> ENCODER =
      new BinaryMessageEncoder<HoodieCompactionMetadata>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<HoodieCompactionMetadata> DECODER =
      new BinaryMessageDecoder<HoodieCompactionMetadata>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<HoodieCompactionMetadata> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<HoodieCompactionMetadata> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<HoodieCompactionMetadata>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this HoodieCompactionMetadata to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a HoodieCompactionMetadata from a ByteBuffer. */
  public static HoodieCompactionMetadata fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieCompactionWriteStat>> partitionToCompactionWriteStats;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public HoodieCompactionMetadata() {}

  /**
   * All-args constructor.
   * @param partitionToCompactionWriteStats The new value for partitionToCompactionWriteStats
   */
  public HoodieCompactionMetadata(java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieCompactionWriteStat>> partitionToCompactionWriteStats) {
    this.partitionToCompactionWriteStats = partitionToCompactionWriteStats;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return partitionToCompactionWriteStats;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: partitionToCompactionWriteStats = (java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieCompactionWriteStat>>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'partitionToCompactionWriteStats' field.
   * @return The value of the 'partitionToCompactionWriteStats' field.
   */
  public java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieCompactionWriteStat>> getPartitionToCompactionWriteStats() {
    return partitionToCompactionWriteStats;
  }

  /**
   * Sets the value of the 'partitionToCompactionWriteStats' field.
   * @param value the value to set.
   */
  public void setPartitionToCompactionWriteStats(java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieCompactionWriteStat>> value) {
    this.partitionToCompactionWriteStats = value;
  }

  /**
   * Creates a new HoodieCompactionMetadata RecordBuilder.
   * @return A new HoodieCompactionMetadata RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieCompactionMetadata.Builder newBuilder() {
    return new org.apache.hudi.avro.model.HoodieCompactionMetadata.Builder();
  }

  /**
   * Creates a new HoodieCompactionMetadata RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new HoodieCompactionMetadata RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieCompactionMetadata.Builder newBuilder(org.apache.hudi.avro.model.HoodieCompactionMetadata.Builder other) {
    return new org.apache.hudi.avro.model.HoodieCompactionMetadata.Builder(other);
  }

  /**
   * Creates a new HoodieCompactionMetadata RecordBuilder by copying an existing HoodieCompactionMetadata instance.
   * @param other The existing instance to copy.
   * @return A new HoodieCompactionMetadata RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieCompactionMetadata.Builder newBuilder(org.apache.hudi.avro.model.HoodieCompactionMetadata other) {
    return new org.apache.hudi.avro.model.HoodieCompactionMetadata.Builder(other);
  }

  /**
   * RecordBuilder for HoodieCompactionMetadata instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<HoodieCompactionMetadata>
    implements org.apache.avro.data.RecordBuilder<HoodieCompactionMetadata> {

    private java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieCompactionWriteStat>> partitionToCompactionWriteStats;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieCompactionMetadata.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.partitionToCompactionWriteStats)) {
        this.partitionToCompactionWriteStats = data().deepCopy(fields()[0].schema(), other.partitionToCompactionWriteStats);
        fieldSetFlags()[0] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing HoodieCompactionMetadata instance
     * @param other The existing instance to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieCompactionMetadata other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.partitionToCompactionWriteStats)) {
        this.partitionToCompactionWriteStats = data().deepCopy(fields()[0].schema(), other.partitionToCompactionWriteStats);
        fieldSetFlags()[0] = true;
      }
    }

    /**
      * Gets the value of the 'partitionToCompactionWriteStats' field.
      * @return The value.
      */
    public java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieCompactionWriteStat>> getPartitionToCompactionWriteStats() {
      return partitionToCompactionWriteStats;
    }

    /**
      * Sets the value of the 'partitionToCompactionWriteStats' field.
      * @param value The value of 'partitionToCompactionWriteStats'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionMetadata.Builder setPartitionToCompactionWriteStats(java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieCompactionWriteStat>> value) {
      validate(fields()[0], value);
      this.partitionToCompactionWriteStats = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'partitionToCompactionWriteStats' field has been set.
      * @return True if the 'partitionToCompactionWriteStats' field has been set, false otherwise.
      */
    public boolean hasPartitionToCompactionWriteStats() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'partitionToCompactionWriteStats' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionMetadata.Builder clearPartitionToCompactionWriteStats() {
      partitionToCompactionWriteStats = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HoodieCompactionMetadata build() {
      try {
        HoodieCompactionMetadata record = new HoodieCompactionMetadata();
        record.partitionToCompactionWriteStats = fieldSetFlags()[0] ? this.partitionToCompactionWriteStats : (java.util.Map<java.lang.CharSequence,java.util.List<org.apache.hudi.avro.model.HoodieCompactionWriteStat>>) defaultValue(fields()[0]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<HoodieCompactionMetadata>
    WRITER$ = (org.apache.avro.io.DatumWriter<HoodieCompactionMetadata>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<HoodieCompactionMetadata>
    READER$ = (org.apache.avro.io.DatumReader<HoodieCompactionMetadata>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
