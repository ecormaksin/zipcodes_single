package com.example.zipcodes.ui.presentation.prefecture;

import static com.example.zipcodes.ui.presentation.EndpointUrls.PREFECTURES;
import static com.example.zipcodes.ui.presentation.KeyNames.KEYWORDS;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.zipcodes.domain.model.prefecture.Prefecture;
import com.example.zipcodes.ui.presentation.ControllerUtil;
import com.example.zipcodes.usecase.prefecture.PrefectureGetListUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PrefectureGetListController {

    private final PrefectureGetListUseCase prefectureGetListUseCase;
    private final PrefectureDtoMapper prefectureDtoMapper;
    private final ControllerUtil controllerUtil;

    @GetMapping(PREFECTURES)
    public ResponseEntity<?> findByKeywords(@RequestParam(name = KEYWORDS) final Optional<String> optionalKeywords) {

        // @formatter:off
        List<Prefecture> entities = optionalKeywords.isPresent() ?
                prefectureGetListUseCase.findByKeywords(optionalKeywords.get())
                : prefectureGetListUseCase.findAll();
        // @formatter:on

        List<PrefectureDto> dtos = prefectureDtoMapper.fromDomainObjectListToDtoList(entities);

        // @formatter:off
        return dtos.isEmpty() ?
                controllerUtil.notFound("message.prefectures.not.found")
                : controllerUtil.ok(dtos);
        // @formatter:on
    }
}
