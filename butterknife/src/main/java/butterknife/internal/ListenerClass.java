package butterknife.internal;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME) @Target(ANNOTATION_TYPE)
public @interface ListenerClass {
  String targetType();

  /** Name of the setter method on the {@link #targetType() target type} for the listener. */
  String setter();

  /** Fully-qualified class name of the listener type. */
  String type();

  /** The number of generic arguments for the type. This used used for casting the view. */
  int genericArguments() default 0;

  /** Enum which declares the listener callback methods. */
  Class<? extends Enum<?>> callbacks();
}
