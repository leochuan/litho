/*
 * Copyright (c) 2017-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package com.facebook.litho.specmodels.model;

import com.facebook.litho.annotations.ResType;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import java.lang.annotation.Annotation;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 * Model that is an abstract representation of a {@link com.facebook.litho.annotations.Prop} that
 * has type Diff.
 */
@Immutable
public class DiffPropModel implements MethodParamModel {
  private final PropModel mUnderlyingPropModel;

  DiffPropModel(PropModel underlyingPropModel) {
    mUnderlyingPropModel = underlyingPropModel;
  }

  @Override
  public TypeName getType() {
    return ParameterizedTypeName.get(ClassNames.DIFF, mUnderlyingPropModel.getType().box());
  }

  @Override
  public String getName() {
    return mUnderlyingPropModel.getName();
  }

  @Override
  public List<Annotation> getAnnotations() {
    return mUnderlyingPropModel.getAnnotations();
  }

  @Override
  public List<AnnotationSpec> getExternalAnnotations() {
    return mUnderlyingPropModel.getExternalAnnotations();
  }

  @Override
  public Object getRepresentedObject() {
    return mUnderlyingPropModel.getRepresentedObject();
  }

  public PropModel getUnderlyingPropModel() {
    return mUnderlyingPropModel;
  }

  public boolean isOptional() {
    return mUnderlyingPropModel.isOptional();
  }

  public ResType getResType() {
    return mUnderlyingPropModel.getResType();
  }

  public boolean hasVarArgs() {
    return !mUnderlyingPropModel.hasVarArgs();
  }

  public String getVarArgsSingleName() {
    return mUnderlyingPropModel.getVarArgsSingleName();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof DiffPropModel) {
      final DiffPropModel p = (DiffPropModel) o;
      return mUnderlyingPropModel.equals(p.mUnderlyingPropModel);
    }

    return false;
  }

  @Override
  public int hashCode() {
    return mUnderlyingPropModel.hashCode();
  }

  public boolean isSameUnderlyingPropModel(PropModel propModel) {
    return propModel.getName().equals(getName()) &&
        propModel.getType().box().equals(mUnderlyingPropModel.getType().box()) &&
        propModel.isOptional() == isOptional() &&
        propModel.getResType() == getResType() &&
        propModel.getVarArgsSingleName().equals(getVarArgsSingleName());
  }
}
