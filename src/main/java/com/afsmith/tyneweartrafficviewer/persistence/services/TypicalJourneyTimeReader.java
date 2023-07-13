package com.afsmith.tyneweartrafficviewer.persistence.services;

import com.afsmith.tyneweartrafficviewer.persistence.entities.TypicalJourneyTime;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Read data on typical and average journey times from the CSV format in which
 * it is provided into the application. This data is provided at {@see https://www.netraveldata.co.uk/?page_id=44}.
 * For each link section, average travel times are provided at 5 minute intervals
 * for both week days and weekends.
 */
public class TypicalJourneyTimeReader {

    /**
     * Read typical journey time data from the provided file.
     * @param filename The path to the data CSV file. Relative to the working
     *                 directory.
     * @param isWeekend Is this weekend data?
     * @return A list of typical journey time entities.
     */
    public List<TypicalJourneyTime> read(String filename, boolean isWeekend) throws IOException {
        Path dataPath = Paths.get(System.getProperty("user.dir"), filename);
        return readCsv(Files.newBufferedReader(dataPath), isWeekend);
    }

    /**
     * Read typical journey time data from a provided Reader object representing
     * the contents of a CSV file.
     * @param reader The Reader object.
     * @param isWeekend Is this weekend data?
     * @return A list of typical journey time entities.
     */
    public List<TypicalJourneyTime> readCsv(Reader reader, boolean isWeekend) {
        String profile = isWeekend ? "weekend" : "weekday";
        List<TypicalJourneyTime> data = new CsvToBeanBuilder<TypicalJourneyTime>(reader)
                .withProfile(profile)
                                        .withType(TypicalJourneyTime.class)
                                        .build()
                                        .parse();

        return data.stream().filter(e -> !"".equals(e.getSystemCodeNumber()))
                   .peek(e -> e.setWeekend(isWeekend))
                   .toList();
    }
}
