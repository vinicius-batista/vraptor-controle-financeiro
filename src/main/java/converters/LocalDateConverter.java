package converters;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.ConversionException;
import br.com.caelum.vraptor.converter.ConversionMessage;
import br.com.caelum.vraptor.converter.Converter;

@Convert(LocalDate.class)
@RequestScoped
public class LocalDateConverter implements Converter<LocalDate> {

  private final Locale locale;

  /**
   * @deprecated CDI eyes only
   */
  protected LocalDateConverter() {
    this(null);
  }

  @Inject
  public LocalDateConverter(Locale locale) {
    this.locale = locale;
  }

  @Override
  public LocalDate convert(String value, Class<? extends LocalDate> type) {
    if (isNullOrEmpty(value)) {
      return null;
    }

    try {
      return LocalDate.parse(value, getFormatter());
    } catch (DateTimeParseException e) {
      throw new ConversionException(new ConversionMessage("is_not_a_valid_date", value));
    }
  }

  protected DateTimeFormatter getFormatter() {
    return DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale);
  }
}
