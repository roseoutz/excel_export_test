package com.example.domain

import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.streaming.SXSSFWorkbook
import org.springframework.stereotype.Service

@Service
class DataExcelService(
    private val dataRepository: DataRepository
) {


    fun export(): Workbook {
        val workBook = SXSSFWorkbook()
        val sheet = workBook.createSheet("sheet1")
        sheet.defaultColumnWidth = 5

        val headers = arrayOf("header1", "header2", "header3", "header4", "header5")

        val headerRow = sheet.createRow(0)

        headers.forEachIndexed { index, value ->
            val cell = headerRow.createCell(index)
            cell.setCellValue(value)
        }

        val entities = dataRepository.findAll()

        entities.forEachIndexed{index, entity ->
            val bodyRow = sheet.createRow(index + 1)
            entity.toStringArray()
                .forEachIndexed{ idx, data ->
                    val cell = bodyRow.createCell(idx)
                    cell.setCellValue(data)
                }
        }

        return workBook
    }

    fun exportByStream(): Workbook {
        val workBook = SXSSFWorkbook()
        val sheet = workBook.createSheet("sheet1")
        sheet.defaultColumnWidth = 5

        val headers = arrayOf("header1", "header2", "header3", "header4", "header5")

        val headerRow = sheet.createRow(0)

        headers.forEachIndexed { index, value ->
            val cell = headerRow.createCell(index)
            cell.setCellValue(value)
        }


        dataRepository.findAll().forEachIndexed{index, entity ->
            val bodyRow = sheet.createRow(index + 1)
            entity.toStringArray()
                .forEachIndexed{ idx, data ->
                    val cell = bodyRow.createCell(idx)
                    cell.setCellValue(data)
                }
        }

        return workBook
    }
}