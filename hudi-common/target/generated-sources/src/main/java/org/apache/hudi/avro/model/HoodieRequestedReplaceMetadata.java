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
public class HoodieRequestedReplaceMetadata extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 8515750905390484512L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieRequestedReplaceMetadata\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"operationType\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"clusteringPlan\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieClusteringPlan\",\"fields\":[{\"name\":\"inputGroups\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieClusteringGroup\",\"fields\":[{\"name\":\"slices\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieSliceInfo\",\"fields\":[{\"name\":\"dataFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"deltaFilePaths\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"default\":null},{\"name\":\"fileId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"bootstrapFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}}],\"default\":null},{\"name\":\"metrics\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"double\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"numOutputFileGroups\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}}],\"default\":null},{\"name\":\"strategy\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieClusteringStrategy\",\"fields\":[{\"name\":\"strategyClassName\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"strategyParams\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}],\"default\":null},{\"name\":\"extraMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"preserveHoodieMetadata\",\"type\":[\"null\",\"boolean\"],\"default\":null}]}],\"default\":null},{\"name\":\"extraMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<HoodieRequestedReplaceMetadata> ENCODER =
      new BinaryMessageEncoder<HoodieRequestedReplaceMetadata>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<HoodieRequestedReplaceMetadata> DECODER =
      new BinaryMessageDecoder<HoodieRequestedReplaceMetadata>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<HoodieRequestedReplaceMetadata> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<HoodieRequestedReplaceMetadata> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<HoodieRequestedReplaceMetadata>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this HoodieRequestedReplaceMetadata to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a HoodieRequestedReplaceMetadata from a ByteBuffer. */
  public static HoodieRequestedReplaceMetadata fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.String operationType;
  @Deprecated public org.apache.hudi.avro.model.HoodieClusteringPlan clusteringPlan;
  @Deprecated public java.util.Map<java.lang.String,java.lang.String> extraMetadata;
  @Deprecated public java.lang.Integer version;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public HoodieRequestedReplaceMetadata() {}

  /**
   * All-args constructor.
   * @param operationType The new value for operationType
   * @param clusteringPlan The new value for clusteringPlan
   * @param extraMetadata The new value for extraMetadata
   * @param version The new value for version
   */
  public HoodieRequestedReplaceMetadata(java.lang.String operationType, org.apache.hudi.avro.model.HoodieClusteringPlan clusteringPlan, java.util.Map<java.lang.String,java.lang.String> extraMetadata, java.lang.Integer version) {
    this.operationType = operationType;
    this.clusteringPlan = clusteringPlan;
    this.extraMetadata = extraMetadata;
    this.version = version;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return operationType;
    case 1: return clusteringPlan;
    case 2: return extraMetadata;
    case 3: return version;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: operationType = (java.lang.String)value$; break;
    case 1: clusteringPlan = (org.apache.hudi.avro.model.HoodieClusteringPlan)value$; break;
    case 2: extraMetadata = (java.util.Map<java.lang.String,java.lang.String>)value$; break;
    case 3: version = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'operationType' field.
   * @return The value of the 'operationType' field.
   */
  public java.lang.String getOperationType() {
    return operationType;
  }

  /**
   * Sets the value of the 'operationType' field.
   * @param value the value to set.
   */
  public void setOperationType(java.lang.String value) {
    this.operationType = value;
  }

  /**
   * Gets the value of the 'clusteringPlan' field.
   * @return The value of the 'clusteringPlan' field.
   */
  public org.apache.hudi.avro.model.HoodieClusteringPlan getClusteringPlan() {
    return clusteringPlan;
  }

  /**
   * Sets the value of the 'clusteringPlan' field.
   * @param value the value to set.
   */
  public void setClusteringPlan(org.apache.hudi.avro.model.HoodieClusteringPlan value) {
    this.clusteringPlan = value;
  }

  /**
   * Gets the value of the 'extraMetadata' field.
   * @return The value of the 'extraMetadata' field.
   */
  public java.util.Map<java.lang.String,java.lang.String> getExtraMetadata() {
    return extraMetadata;
  }

  /**
   * Sets the value of the 'extraMetadata' field.
   * @param value the value to set.
   */
  public void setExtraMetadata(java.util.Map<java.lang.String,java.lang.String> value) {
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
   * Creates a new HoodieRequestedReplaceMetadata RecordBuilder.
   * @return A new HoodieRequestedReplaceMetadata RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata.Builder newBuilder() {
    return new org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata.Builder();
  }

  /**
   * Creates a new HoodieRequestedReplaceMetadata RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new HoodieRequestedReplaceMetadata RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata.Builder newBuilder(org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata.Builder other) {
    return new org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata.Builder(other);
  }

  /**
   * Creates a new HoodieRequestedReplaceMetadata RecordBuilder by copying an existing HoodieRequestedReplaceMetadata instance.
   * @param other The existing instance to copy.
   * @return A new HoodieRequestedReplaceMetadata RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata.Builder newBuilder(org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata other) {
    return new org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata.Builder(other);
  }

  /**
   * RecordBuilder for HoodieRequestedReplaceMetadata instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<HoodieRequestedReplaceMetadata>
    implements org.apache.avro.data.RecordBuilder<HoodieRequestedReplaceMetadata> {

    private java.lang.String operationType;
    private org.apache.hudi.avro.model.HoodieClusteringPlan clusteringPlan;
    private org.apache.hudi.avro.model.HoodieClusteringPlan.Builder clusteringPlanBuilder;
    private java.util.Map<java.lang.String,java.lang.String> extraMetadata;
    private java.lang.Integer version;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.operationType)) {
        this.operationType = data().deepCopy(fields()[0].schema(), other.operationType);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.clusteringPlan)) {
        this.clusteringPlan = data().deepCopy(fields()[1].schema(), other.clusteringPlan);
        fieldSetFlags()[1] = true;
      }
      if (other.hasClusteringPlanBuilder()) {
        this.clusteringPlanBuilder = org.apache.hudi.avro.model.HoodieClusteringPlan.newBuilder(other.getClusteringPlanBuilder());
      }
      if (isValidValue(fields()[2], other.extraMetadata)) {
        this.extraMetadata = data().deepCopy(fields()[2].schema(), other.extraMetadata);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.version)) {
        this.version = data().deepCopy(fields()[3].schema(), other.version);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing HoodieRequestedReplaceMetadata instance
     * @param other The existing instance to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.operationType)) {
        this.operationType = data().deepCopy(fields()[0].schema(), other.operationType);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.clusteringPlan)) {
        this.clusteringPlan = data().deepCopy(fields()[1].schema(), other.clusteringPlan);
        fieldSetFlags()[1] = true;
      }
      this.clusteringPlanBuilder = null;
      if (isValidValue(fields()[2], other.extraMetadata)) {
        this.extraMetadata = data().deepCopy(fields()[2].schema(), other.extraMetadata);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.version)) {
        this.version = data().deepCopy(fields()[3].schema(), other.version);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'operationType' field.
      * @return The value.
      */
    public java.lang.String getOperationType() {
      return operationType;
    }

    /**
      * Sets the value of the 'operationType' field.
      * @param value The value of 'operationType'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata.Builder setOperationType(java.lang.String value) {
      validate(fields()[0], value);
      this.operationType = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'operationType' field has been set.
      * @return True if the 'operationType' field has been set, false otherwise.
      */
    public boolean hasOperationType() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'operationType' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata.Builder clearOperationType() {
      operationType = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'clusteringPlan' field.
      * @return The value.
      */
    public org.apache.hudi.avro.model.HoodieClusteringPlan getClusteringPlan() {
      return clusteringPlan;
    }

    /**
      * Sets the value of the 'clusteringPlan' field.
      * @param value The value of 'clusteringPlan'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata.Builder setClusteringPlan(org.apache.hudi.avro.model.HoodieClusteringPlan value) {
      validate(fields()[1], value);
      this.clusteringPlanBuilder = null;
      this.clusteringPlan = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'clusteringPlan' field has been set.
      * @return True if the 'clusteringPlan' field has been set, false otherwise.
      */
    public boolean hasClusteringPlan() {
      return fieldSetFlags()[1];
    }

    /**
     * Gets the Builder instance for the 'clusteringPlan' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public org.apache.hudi.avro.model.HoodieClusteringPlan.Builder getClusteringPlanBuilder() {
      if (clusteringPlanBuilder == null) {
        if (hasClusteringPlan()) {
          setClusteringPlanBuilder(org.apache.hudi.avro.model.HoodieClusteringPlan.newBuilder(clusteringPlan));
        } else {
          setClusteringPlanBuilder(org.apache.hudi.avro.model.HoodieClusteringPlan.newBuilder());
        }
      }
      return clusteringPlanBuilder;
    }

    /**
     * Sets the Builder instance for the 'clusteringPlan' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata.Builder setClusteringPlanBuilder(org.apache.hudi.avro.model.HoodieClusteringPlan.Builder value) {
      clearClusteringPlan();
      clusteringPlanBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'clusteringPlan' field has an active Builder instance
     * @return True if the 'clusteringPlan' field has an active Builder instance
     */
    public boolean hasClusteringPlanBuilder() {
      return clusteringPlanBuilder != null;
    }

    /**
      * Clears the value of the 'clusteringPlan' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata.Builder clearClusteringPlan() {
      clusteringPlan = null;
      clusteringPlanBuilder = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'extraMetadata' field.
      * @return The value.
      */
    public java.util.Map<java.lang.String,java.lang.String> getExtraMetadata() {
      return extraMetadata;
    }

    /**
      * Sets the value of the 'extraMetadata' field.
      * @param value The value of 'extraMetadata'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata.Builder setExtraMetadata(java.util.Map<java.lang.String,java.lang.String> value) {
      validate(fields()[2], value);
      this.extraMetadata = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'extraMetadata' field has been set.
      * @return True if the 'extraMetadata' field has been set, false otherwise.
      */
    public boolean hasExtraMetadata() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'extraMetadata' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata.Builder clearExtraMetadata() {
      extraMetadata = null;
      fieldSetFlags()[2] = false;
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
    public org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata.Builder setVersion(java.lang.Integer value) {
      validate(fields()[3], value);
      this.version = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'version' field has been set.
      * @return True if the 'version' field has been set, false otherwise.
      */
    public boolean hasVersion() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'version' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata.Builder clearVersion() {
      version = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HoodieRequestedReplaceMetadata build() {
      try {
        HoodieRequestedReplaceMetadata record = new HoodieRequestedReplaceMetadata();
        record.operationType = fieldSetFlags()[0] ? this.operationType : (java.lang.String) defaultValue(fields()[0]);
        if (clusteringPlanBuilder != null) {
          record.clusteringPlan = this.clusteringPlanBuilder.build();
        } else {
          record.clusteringPlan = fieldSetFlags()[1] ? this.clusteringPlan : (org.apache.hudi.avro.model.HoodieClusteringPlan) defaultValue(fields()[1]);
        }
        record.extraMetadata = fieldSetFlags()[2] ? this.extraMetadata : (java.util.Map<java.lang.String,java.lang.String>) defaultValue(fields()[2]);
        record.version = fieldSetFlags()[3] ? this.version : (java.lang.Integer) defaultValue(fields()[3]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<HoodieRequestedReplaceMetadata>
    WRITER$ = (org.apache.avro.io.DatumWriter<HoodieRequestedReplaceMetadata>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<HoodieRequestedReplaceMetadata>
    READER$ = (org.apache.avro.io.DatumReader<HoodieRequestedReplaceMetadata>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}