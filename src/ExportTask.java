import java.sql.Connection;
import java.sql.ResultSet;
import java.util.concurrent.locks.ReentrantLock;

public class ExportTask implements Runnable {

    private final int requestId;
    private final ReentrantLock excelLock;

    public ExportTask(int requestId, ReentrantLock excelLock) {
        this.requestId = requestId;
        this.excelLock = excelLock;
    }

    @Override
    public void run() {
        String outputFileName = "Exported_EmployeesData" + requestId + ".xlsx";

        try (Connection connection = DatabaseConnector.getConnection()) {
            // Retrieve data
            ResultSet resultSet = DatabaseConnector.getEmployeeData(connection);

            // Ensure thread-safe access to the Excel file
            excelLock.lock();
            try {
                ExcelExporter.exportToExcel(resultSet, outputFileName);
                System.out.println("Request " + requestId + ": Data exported to " + outputFileName);
            } finally {
                excelLock.unlock();
            }

        } catch (Exception e) {
            System.err.println("Request " + requestId + ": Error - " + e.getMessage());
        }
    }
}