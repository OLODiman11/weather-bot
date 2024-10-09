package net.azovdv.weatherbot.model;

public record Main(
        double temp,
        double feels_like,
        double pressure,
        double humidity
){}
