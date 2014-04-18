package butterknife;

import android.text.TextWatcher;
import butterknife.internal.ListenerClass;
import butterknife.internal.ListenerMethod;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Annotation for methods which indicate that they should be called when text has changed.
 * Corresponds to adding an {@link TextWatcher TextWatcher} to the views specified.
 * <pre><code>
 * {@literal @}OnTextChanged(R.id.example) void onTextChanged(CharSequence text) {
 *   Toast.makeText(this, "Text changed: " + text, LENGTH_SHORT).show();
 * }
 * </code></pre>
 * Any number of parameters from {@link TextWatcher#onTextChanged(CharSequence, int, int, int)
 * onTextChanged} may be used on the method.
 * <p>
 * To bind to methods other than {@code onTextChanged}, specify a different {@code callback}.
 * <pre><code>
 * {@literal @}OnTextChanged(value = R.id.example, callback = BEFORE_TEXT_CHANGED)
 * void onBeforeTextChanged(CharSequence text {
 *   Toast.makeText(this, "Before text changed: " + text, LENGTH_SHORT).show();
 * }
 * </code></pre>
 *
 * @see TextWatcher
 * @see Optional
 */
@Target(METHOD)
@Retention(CLASS)
@ListenerClass(
    targetType = "android.widget.TextView",
    setter = "addTextChangedListener",
    type = "android.text.TextWatcher",
    callbacks = OnTextChanged.Callback.class
)
public @interface OnTextChanged {
  int[] value();
  Callback callback() default Callback.TEXT_CHANGED;

  enum Callback {
    @ListenerMethod(
        name = "onTextChanged",
        parameters = {
            "java.lang.CharSequence",
            "int",
            "int",
            "int"
        }
    )
    TEXT_CHANGED,

    @ListenerMethod(
        name = "beforeTextChanged",
        parameters = {
            "java.lang.CharSequence",
            "int",
            "int",
            "int"
        }
    )
    BEFORE_TEXT_CHANGED,

    @ListenerMethod(
        name = "afterTextChanged",
        parameters = "android.text.Editable"
    )
    AFTER_TEXT_CHANGED,
  }
}
