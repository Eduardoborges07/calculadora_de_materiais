package com.exemplo.obra.service;

import com.exemplo.obra.dto.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class MaterialService {

    public ConcretoResponse calcularVolumeConcreto(ConcretoRequest request) {


        double volumeTotal = 0;

        for (var parede : request.getArestas()) {

            double volumeParede = parede.getComprimento() * parede.getEspessura() * request.getAlturaViga();
            volumeTotal += volumeParede;
        }

        return new ConcretoResponse(
                new BigDecimal(volumeTotal).setScale(2, RoundingMode.HALF_UP),
                request.getArestas().size(),
                "Volume de concreto calculado com sucesso."
        );
    }

    public TijoloResponse calcularQuantidadeTijolos(TijoloRequest request) {


        double areaLiquidaTotal = 0;
        double areaAberturasTotal = 0;

        for (var parede : request.getArestas()) {
            double areaBruta = parede.getComprimento() * parede.getAlturaParede();


            double areaJanela = parede.getComprimento() * parede.getAlturaJanela();
            double areaPorta = parede.getComprimento() * parede.getAlturaPorta();

            areaAberturasTotal += (areaJanela + areaPorta);
            areaLiquidaTotal += (areaBruta - areaJanela - areaPorta);
        }


        double areaUmTijolo = request.getLarguraTijolo() * request.getAlturaTijolo();


        int quantidadeTijolos = (int) Math.ceil(areaLiquidaTotal / areaUmTijolo);

        return new TijoloResponse(
                new BigDecimal(areaLiquidaTotal).setScale(2, RoundingMode.HALF_UP),
                new BigDecimal(areaAberturasTotal).setScale(2, RoundingMode.HALF_UP),
                new BigDecimal(areaUmTijolo).setScale(4, RoundingMode.HALF_UP),
                quantidadeTijolos,
                request.getArestas().size(),
                "Quantidade de tijolos calculada com sucesso."
        );
    }
}
