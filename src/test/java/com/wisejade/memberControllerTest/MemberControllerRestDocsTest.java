package com.wisejade.memberControllerTest;

import com.google.gson.Gson;
import com.wisejade.api.member.controller.MemberController;
import com.wisejade.api.member.dto.MemberPostDto;
import com.wisejade.api.member.dto.MemberResponseDto;
import com.wisejade.api.member.entity.City;
import com.wisejade.api.member.entity.IndustryType;
import com.wisejade.api.member.entity.Member;
import com.wisejade.api.member.mapper.MemberMapper;
import com.wisejade.api.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MemberControllerRestDocsTest {
    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper mapper;

    @Autowired
    private Gson gson;

    private City seoul = new City("001", "??????");
    private City gyeonggido = new City("002", "?????????");

    private IndustryType service = new IndustryType("005", "????????????");
    private IndustryType sale = new IndustryType("002", "????????????");


    private Member member1 = new Member(1L, "?????????", "s4goodbye!", "m", "????????????????????????", service, seoul);
    private Member member2 = new Member(2L, "?????????", "java123", "f", "????????????", sale, gyeonggido);
    private Member member3 = new Member(3L, "????????????", "p33ddd@", "m", "?????????????????????", service, gyeonggido);
    private Member member4 = new Member(4L, "?????????", "this233$", "m", "?????????????????????", service, seoul);

    MemberResponseDto member1Dto = new MemberResponseDto(1L, "?????????", "m", "????????????????????????", service, seoul);
    MemberResponseDto member2Dto = new MemberResponseDto(2L, "?????????", "f", "????????????", sale, gyeonggido);
    MemberResponseDto member3Dto = new MemberResponseDto(3L, "????????????", "m", "?????????????????????", service, gyeonggido);
    MemberResponseDto member4Dto = new MemberResponseDto(4L, "?????????", "m", "?????????????????????", sale, seoul);


    @Test
    public void getMembers() throws Exception {
        // given
        List<Member> members = List.of(member1, member2, member3, member4);
        List<MemberResponseDto> memberResponseDtos = List.of(member1Dto, member2Dto, member3Dto, member4Dto);
        given(memberService.findAll()).willReturn(members);
        given(mapper.membersToMemberResponseDtos(Mockito.anyList())).willReturn(memberResponseDtos);


        // when
        ResultActions actions =
                mockmvc.perform(get("/v1/members")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                );
        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(
                        document("get-members",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                responseFields(
                                        List.of(
                                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("?????? ?????????"),
                                                fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("data[].name").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("data[].sex").type(JsonFieldType.STRING).description("?????? ??????: m(???) / f(???)"),
                                                fieldWithPath("data[].companyName").type(JsonFieldType.STRING).description("????????? ??????"),
                                                fieldWithPath("data[].companyType").type(JsonFieldType.OBJECT).description("?????? ??????"),
                                                fieldWithPath("data[].companyType.typeId").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("data[].companyType.name").type(JsonFieldType.STRING).description("?????????"),
                                                fieldWithPath("data[].companyLocation").type(JsonFieldType.OBJECT).description("?????? ??????(??????)"),
                                                fieldWithPath("data[].companyLocation.cityId").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("data[].companyLocation.name").type(JsonFieldType.STRING).description("?????????")
                                        )
                                )
                        )
                );
    }

    @Test
    public void getMembersByLocation() throws Exception {
        // given
        // ???????????? ?????????
        List<Member> members = List.of(member2, member3);
        List<MemberResponseDto> memberResponseDtos = List.of(member2Dto, member3Dto);
        given(memberService.findByLocation(Mockito.anyString())).willReturn(members);
        given(mapper.membersToMemberResponseDtos(Mockito.anyList())).willReturn(memberResponseDtos);

        // when
        ResultActions actions =
                mockmvc.perform(get("/v1/members/location")
                        .param("companyLocation", gyeonggido.getCityId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                );
        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(
                        document("get-members-by-location",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                responseFields(
                                        List.of(
                                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("?????? ?????????"),
                                                fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("data[].name").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("data[].sex").type(JsonFieldType.STRING).description("?????? ??????: m(???) / f(???)"),
                                                fieldWithPath("data[].companyName").type(JsonFieldType.STRING).description("????????? ??????"),
                                                fieldWithPath("data[].companyType").type(JsonFieldType.OBJECT).description("?????? ??????"),
                                                fieldWithPath("data[].companyType.typeId").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("data[].companyType.name").type(JsonFieldType.STRING).description("?????????"),
                                                fieldWithPath("data[].companyLocation").type(JsonFieldType.OBJECT).description("?????? ??????(??????)"),
                                                fieldWithPath("data[].companyLocation.cityId").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("data[].companyLocation.name").type(JsonFieldType.STRING).description("?????????")
                                        )
                                )
                        )
                );
    }

    @Test
    public void getMembersByType() throws Exception {
        // given
        // ????????????????????? ?????????
        List<Member> members = List.of(member1, member3, member4);
        List<MemberResponseDto> memberResponseDtos = List.of(member1Dto, member3Dto, member4Dto);
        given(memberService.findByType(Mockito.anyString())).willReturn(members);
        given(mapper.membersToMemberResponseDtos(Mockito.anyList())).willReturn(memberResponseDtos);

        // when
        ResultActions actions =
                mockmvc.perform(get("/v1/members/type")
                        .param("companyType", service.getTypeId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                );
        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(
                        document("get-members-by-type",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                responseFields(
                                        List.of(
                                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("?????? ?????????"),
                                                fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("data[].name").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("data[].sex").type(JsonFieldType.STRING).description("?????? ??????: m(???) / f(???)"),
                                                fieldWithPath("data[].companyName").type(JsonFieldType.STRING).description("????????? ??????"),
                                                fieldWithPath("data[].companyType").type(JsonFieldType.OBJECT).description("?????? ??????"),
                                                fieldWithPath("data[].companyType.typeId").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("data[].companyType.name").type(JsonFieldType.STRING).description("?????????"),
                                                fieldWithPath("data[].companyLocation").type(JsonFieldType.OBJECT).description("?????? ??????(??????)"),
                                                fieldWithPath("data[].companyLocation.cityId").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("data[].companyLocation.name").type(JsonFieldType.STRING).description("?????????")
                                        )
                                )
                        )
                );
    }

    @Test
    public void postMember() throws Exception {
        // given
        Member member = Member.builder()
                .name("?????????")
                .password("java123")
                .sex("f")
                .companyName("?????????????????????")
                .build();
        member.setCompanyLocation(new City(gyeonggido.getCityId(), ""));
        member.setCompanyType(new IndustryType(sale.getTypeId(), ""));
        MemberPostDto postDto = new MemberPostDto("?????????", "java123", "f", "?????????????????????", sale.getTypeId(), gyeonggido.getCityId());

        given(mapper.memberPostDtoToMember(Mockito.mock(MemberPostDto.class))).willReturn(member);
        given(memberService.createMember(Mockito.any())).willReturn(member2);
        given(mapper.memberToMemberResponseDto(Mockito.any())).willReturn(member2Dto);

        String content = gson.toJson(postDto);

        // when
        ResultActions actions =
                mockmvc.perform(post("/v1/members")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.memberId").value(member2Dto.getMemberId()))
                .andExpect(jsonPath("$.data.name").value(member2Dto.getName()))
                .andExpect(jsonPath("$.data.sex").value(member2Dto.getSex()))
                .andExpect(jsonPath("$.data.companyName").value(member2Dto.getCompanyName()))
                .andExpect(jsonPath("$.data.companyType.typeId").value(sale.getTypeId()))
                .andExpect(jsonPath("$.data.companyType.name").value(sale.getName()))
                .andExpect(jsonPath("$.data.companyLocation.cityId").value(gyeonggido.getCityId()))
                .andExpect(jsonPath("$.data.companyLocation.name").value(gyeonggido.getName()))
                .andDo(
                        document("post-member",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                responseFields(
                                        List.of(
                                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????"),
                                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("data.sex").type(JsonFieldType.STRING).description("?????? ??????: m(???) / f(???)"),
                                                fieldWithPath("data.companyName").type(JsonFieldType.STRING).description("????????? ??????"),
                                                fieldWithPath("data.companyType").type(JsonFieldType.OBJECT).description("?????? ??????"),
                                                fieldWithPath("data.companyType.typeId").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("data.companyType.name").type(JsonFieldType.STRING).description("?????????"),
                                                fieldWithPath("data.companyLocation").type(JsonFieldType.OBJECT).description("?????? ??????(??????)"),
                                                fieldWithPath("data.companyLocation.cityId").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("data.companyLocation.name").type(JsonFieldType.STRING).description("?????????")
                                        )
                                )

                        )
                );

    }

}
