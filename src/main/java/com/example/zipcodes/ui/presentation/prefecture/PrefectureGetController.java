package com.example.zipcodes.ui.presentation.prefecture;

import static com.example.zipcodes.ui.presentation.EndpointUrls.PREFECTURE_GET;
import static com.example.zipcodes.ui.presentation.Names.PREFECTURE_CODE;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.zipcodes.domain.model.prefecture.Prefecture;
import com.example.zipcodes.domain.model.prefecture.PrefectureCode;
import com.example.zipcodes.ui.presentation.ControllerUtil;
import com.example.zipcodes.usecase.prefecture.PrefectureGetUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PrefectureGetController {

    private final PrefectureGetUseCase prefectureGetUseCase;
    private final PrefectureDtoMapper prefectureDtoMapper;
    private final ControllerUtil controllerUtil;

    @GetMapping(PREFECTURE_GET)
    public ResponseEntity<?> get(@PathVariable(name = PREFECTURE_CODE) final String prefectureCodeStr) {

        Optional<Prefecture> optionalPrefecture = prefectureGetUseCase.get(new PrefectureCode(prefectureCodeStr));

        // @formatter:off
        return optionalPrefecture.isPresent() ?
            controllerUtil.ok(prefectureDtoMapper.fromDomainObjectToDto(optionalPrefecture.get()))
            : controllerUtil.notFound("message.prefecture.not.found", new Object[] { prefectureCodeStr });
        // @formatter:on
    }
}
