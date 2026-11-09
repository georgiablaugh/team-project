package entity;

import java.util.EnumMap;
import java.util.Map;

/**
 * Today's weather (from Open-Meteo) and the rule for how it changes the library scores.
 */
public class Weather {
    private final double temperatureCelsius;
    private final boolean raining;

    public Weather(double temperatureCelsius, boolean raining) {
        this.temperatureCelsius = temperatureCelsius;
        this.raining = raining;
    }

    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public boolean isRaining() {
        return raining;
    }

    /**
     * Returns a copy of the scores adjusted for today's weather.
     * Placeholder rule (the team should agree on the real one):
     * rain or below 0°C favours Robarts (stay inside all day); above 20°C favours UC (nice quad).
     */
    public Map<Library, Integer> applyTo(Map<Library, Integer> scores) {
        Map<Library, Integer> adjusted = new EnumMap<>(Library.class);
        adjusted.putAll(scores);
        if (raining || temperatureCelsius < 0) {
            adjusted.merge(Library.ROBARTS, 1, Integer::sum);
        } else if (temperatureCelsius > 20) {
            adjusted.merge(Library.UC, 1, Integer::sum);
        }
        return adjusted;
    }
}
