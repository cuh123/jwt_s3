package com.example.boot.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageModel {

    @ApiModelProperty(value="총 컨텐츠 카운트 - db query row 갯수")
    int total;

    @ApiModelProperty(value="현재 페이지 번호 - << 1, 2, 3, 4 >>")
    int page;

    @ApiModelProperty(value="페이지당 컨텐츠 수 - 화면 하나의 보여줄 컨텐츠의 갯수")
    int count;

    @ApiModelProperty(value="페이지 번호 인덱스 (시작 숫자) ex) page 3, count 10 -> 21")
    int pageIdx;

    @ApiModelProperty(value="조회용 ex) page 3, count 10 -> 20")
    int limit;

    @ApiModelProperty(value="페이지의 카운트 ex) total 213, count 10 = 22")
    int totalPage;

}
