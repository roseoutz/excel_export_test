package com.example.domain

import jakarta.servlet.http.HttpServletResponse
import org.apache.poi.ss.usermodel.Workbook
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class DataController(
    private val dataExcelService: DataExcelService
) {

    private fun sendFile(httpServletResponse: HttpServletResponse, workbook: Workbook) {
        httpServletResponse.contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        httpServletResponse.addHeader(
            "Content-Disposition", "attachment;filename=test.xlsx"
        )
        val outputStream = httpServletResponse.outputStream
        workbook.write(outputStream)
        workbook.close()
        outputStream.flush()
        outputStream.close()
    }

    @GetMapping("/export/stream")
    fun exportByStream(
        httpServletResponse: HttpServletResponse
    ) {

        val workBook = dataExcelService.exportByStream()
        sendFile(httpServletResponse, workBook)
    }

    @GetMapping("/export")
    fun export(
        httpServletResponse: HttpServletResponse
    ) {
        val workBook = dataExcelService.export()
        sendFile(httpServletResponse, workBook)
    }
}