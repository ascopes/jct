/*
 * Copyright (C) 2022 Ashley Scopes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.ascopes.jct.testing.unit.compilers;

import static com.github.ascopes.jct.testing.helpers.MoreMocks.mockCast;
import static com.github.ascopes.jct.testing.helpers.MoreMocks.stubCast;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;

import com.github.ascopes.jct.compilers.Compiler;
import com.github.ascopes.jct.compilers.Compiler.CompilerConfigurer;
import com.github.ascopes.jct.compilers.Compiler.ProcessorDiscovery;
import com.github.ascopes.jct.compilers.SimpleCompilation;
import com.github.ascopes.jct.compilers.SimpleCompiler;
import com.github.ascopes.jct.testing.helpers.TypeRef;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * {@link SimpleCompiler} tests.
 *
 * @author Ashley Scopes
 */
@DisplayName("SimpleCompiler tests")
class SimpleCompilerTest {

  @DisplayName("Default annotation processors are empty")
  @Test
  void defaultAnnotationProcessorsAreEmpty() {
    // Then
    assertThat(new StubbedCompiler().getAnnotationProcessors()).isEmpty();
  }

  @DisplayName("Default annotation processor options are empty")
  @Test
  void defaultAnnotationProcessorOptionsAreEmpty() {
    assertThat(new StubbedCompiler().getAnnotationProcessorOptions()).isEmpty();
  }

  @DisplayName("Default compiler options are empty")
  @Test
  void defaultCompilerOptionsAreEmpty() {
    assertThat(new StubbedCompiler().getCompilerOptions()).isEmpty();
  }

  @DisplayName("Default runtime options are empty")
  @Test
  void defaultRuntimeOptionsAreEmpty() {
    assertThat(new StubbedCompiler().getRuntimeOptions()).isEmpty();
  }

  @DisplayName("Default warnings setting is the expected value")
  @Test
  void defaultWarningsSettingIsExpectedValue() {
    assertThat(new StubbedCompiler().isShowWarnings())
        .isEqualTo(Compiler.DEFAULT_SHOW_WARNINGS);
  }

  @DisplayName("Default deprecation warnings setting is the expected value")
  @Test
  void defaultDeprecationWarningsSettingIsExpectedValue() {
    assertThat(new StubbedCompiler().isShowDeprecationWarnings())
        .isEqualTo(Compiler.DEFAULT_SHOW_DEPRECATION_WARNINGS);
  }

  @DisplayName("Default warnings-as-errors setting is the expected value")
  @Test
  void defaultWarningsAsErrorsSettingIsExpectedValue() {
    assertThat(new StubbedCompiler().isFailOnWarnings())
        .isEqualTo(Compiler.DEFAULT_FAIL_ON_WARNINGS);
  }

  @DisplayName("Default locale setting is the expected value")
  @Test
  void defaultLocaleSettingIsExpectedValue() {
    assertThat(new StubbedCompiler().getLocale())
        .isEqualTo(Compiler.DEFAULT_LOCALE);
  }

  @DisplayName("Default verbose logging setting is the expected value")
  @Test
  void defaultVerboseLoggingSettingIsExpectedValue() {
    assertThat(new StubbedCompiler().isVerbose())
        .isEqualTo(Compiler.DEFAULT_VERBOSE);
  }

  @DisplayName("Default preview features setting is the expected value")
  @Test
  void defaultPreviewFeaturesSettingIsExpectedValue() {
    assertThat(new StubbedCompiler().isPreviewFeatures())
        .isEqualTo(Compiler.DEFAULT_PREVIEW_FEATURES);
  }

  @DisplayName("Default release version is empty")
  @Test
  void defaultReleaseVersionIsEmpty() {
    assertThat(new StubbedCompiler().getRelease()).isNotPresent();
  }

  @DisplayName("Default source version is empty")
  @Test
  void defaultSourceVersionIsEmpty() {
    assertThat(new StubbedCompiler().getSource()).isNotPresent();
  }

  @DisplayName("Default target version is empty")
  @Test
  void defaultTargetVersionIsEmpty() {
    assertThat(new StubbedCompiler().getTarget()).isNotPresent();
  }

  @DisplayName("Default current class path inclusion setting is the expected value")
  @Test
  void defaultCurrentClassPathInclusionSettingIsExpectedValue() {
    assertThat(new StubbedCompiler().isInheritClassPath())
        .isEqualTo(Compiler.DEFAULT_INHERIT_CLASS_PATH);
  }

  @DisplayName("Default current module path inclusion setting is the expected value")
  @Test
  void defaultCurrentModulePathInclusionSettingIsExpectedValue() {
    assertThat(new StubbedCompiler().isInheritModulePath())
        .isEqualTo(Compiler.DEFAULT_INHERIT_MODULE_PATH);
  }

  @DisplayName("Default current platform class path inclusion setting is the expected value")
  @Test
  void defaultCurrentPlatformClassPathInclusionSettingIsExpectedValue() {
    assertThat(new StubbedCompiler().isInheritPlatformClassPath())
        .isEqualTo(Compiler.DEFAULT_INHERIT_PLATFORM_CLASS_PATH);
  }

  @DisplayName("Default system module path inclusion setting is the expected value")
  @Test
  void defaultSystemModulePathInclusionSettingIsExpectedValue() {
    assertThat(new StubbedCompiler().isInheritSystemModulePath())
        .isEqualTo(Compiler.DEFAULT_INHERIT_SYSTEM_MODULE_PATH);
  }

  @DisplayName("Default file manager logging setting is the expected value")
  @Test
  void defaultFileManagerLoggingSettingIsExpectedValue() {
    assertThat(new StubbedCompiler().getFileManagerLogging())
        .isEqualTo(Compiler.DEFAULT_FILE_MANAGER_LOGGING);
  }

  @DisplayName("Default diagnostics logging setting is the expected value")
  @Test
  void defaultDiagnosticsLoggingSettingIsExpectedValue() {
    assertThat(new StubbedCompiler().getDiagnostics())
        .isEqualTo(Compiler.DEFAULT_DIAGNOSTICS);
  }

  @DisplayName("Default enable annotation processor discovery setting is the expected value")
  @Test
  void defaultEnableAnnotationProcessorDiscoveryIsExpectedValue() {
    assertThat(new StubbedCompiler().getAnnotationProcessorDiscovery())
        .isEqualTo(ProcessorDiscovery.INCLUDE_DEPENDENCIES);
  }

  @DisplayName("Applying a configurer invokes the configurer with the compiler")
  @Test
  void applyingConfigurerInvokesConfigurerWithCompiler() throws Exception {
    // Given
    var compiler = new StubbedCompiler();
    var configurer = mockCast(new TypeRef<CompilerConfigurer<StubbedCompiler, Exception>>() {});

    // When
    var result = compiler.configure(configurer);

    // Then
    assertThat(result).isSameAs(compiler);
    then(configurer).should().configure(compiler);
  }

  // Extend to allow field access to protected members.
  static class StubbedCompiler extends SimpleCompiler<StubbedCompiler> {

    protected StubbedCompiler() {
      super("stubbed", stubCast(new TypeRef<>() {}), stubCast(new TypeRef<>() {}));
    }

    @Override
    public SimpleCompilation compile() {
      throw new UnsupportedOperationException();
    }

    @Override
    public String getName() {
      return getClass().getName() + "#" + Integer.toHexString(System.identityHashCode(this));
    }
  }
}
