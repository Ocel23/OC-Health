package ocel23.me.ochealth;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ocel23.me.ochealth.models.Statistics;
import ocel23.me.ochealth.models.statisticsModels.HardwareInfo;
import ocel23.me.ochealth.models.statisticsModels.NetworkInfo;
import ocel23.me.ochealth.models.statisticsModels.PowerSource;
import ocel23.me.ochealth.models.statisticsModels.SoftwareInfo;
import ocel23.me.ochealth.models.statisticsModels.hardwareInfo.GraphicCard;
import ocel23.me.ochealth.models.statisticsModels.hardwareInfo.Memory;
import ocel23.me.ochealth.models.statisticsModels.hardwareInfo.Processor;
import org.yaml.snakeyaml.Yaml;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.NetworkParams;
import oshi.software.os.OperatingSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticsHandler {

    public void createJsonDataFile(String filePath) {


        Statistics statistics = getStatisticsData();

        try {
            File file = new File(filePath);
            file.createNewFile();
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            String json = gson.toJson(statistics);
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void createYamlDataFile(String filePath) {

        Statistics statistics = getStatisticsData();
        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Yaml yaml = new Yaml();
        try {
            PrintWriter printWriter = new PrintWriter(file);
            yaml.dump(statistics, printWriter);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void createTextDataFile(String filePath) {

        Statistics statistics = getStatisticsData();

        File file = new File(filePath);

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileOutputStream fos;
        ObjectOutputStream objectOutputStream;
        try {
            fos = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fos);
            objectOutputStream.writeObject(statistics);
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Statistics getStatisticsData() {
        SystemInfo systemInfo = new SystemInfo();
        GraphicsCard graphicsCard = systemInfo.getHardware().getGraphicsCards().get(0);
        CentralProcessor centralProcessor = systemInfo.getHardware().getProcessor();
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
        Processor processor = new Processor(
                centralProcessor.getProcessorIdentifier().getModel(),
                centralProcessor.getProcessorIdentifier().getFamily(),
                centralProcessor.getProcessorIdentifier().getName(),
                centralProcessor.getProcessorIdentifier().getVendor(),
                (int) centralProcessor.getCurrentFreq()[0],
                centralProcessor.getPhysicalProcessorCount()
        );
        GraphicCard graphicCard = new GraphicCard(
                graphicsCard.getName(),
                graphicsCard.getVendor(),
                graphicsCard.getVersionInfo(),
                graphicsCard.getVRam()
        );
        int count = 0;
        List<String> types = new ArrayList<>();

        for (PhysicalMemory physicalMemory : globalMemory.getPhysicalMemory()) {
            count++;
            types.add(physicalMemory.getMemoryType() + ",");
        }
        Memory memory = new Memory(
                globalMemory.getTotal(),
                globalMemory.getPhysicalMemory().size(),
                types,
                globalMemory.getVirtualMemory().getVirtualInUse()

        );
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        HardwareInfo hardwareInfo = new HardwareInfo(processor, graphicCard, memory);
        SoftwareInfo softwareInfo = new SoftwareInfo(
                operatingSystem.getFamily(),
                operatingSystem.getVersionInfo().getVersion(),
                operatingSystem.getBitness(),
                operatingSystem.getManufacturer(),
                operatingSystem.getProcessCount(),
                operatingSystem.isElevated(),
                operatingSystem.getDesktopWindows(true).size()
        );
        NetworkParams networkParams = operatingSystem.getNetworkParams();
        NetworkIF networkIF = systemInfo.getHardware().getNetworkIFs().get(0);
        String ipv4 = "";
        for (int j = 0; j < networkIF.getIPv4addr().length; j++) {
            ipv4 += networkIF.getIPv4addr()[j];
            if (j == networkIF.getIPv4addr().length - 1) continue;
            ipv4 += ",";
        }

        String ipv6 = "";
        for (int k = 0; k < networkIF.getIPv6addr().length; k++) {
            ipv6 += networkIF.getIPv6addr()[k];
            if (k == networkIF.getIPv6addr().length - 1) continue;
            ipv6 += ",";
        }
        NetworkInfo networkInfo = new NetworkInfo(
                ipv4,
                ipv6,
                networkIF.getMacaddr(),
                networkParams.getIpv4DefaultGateway(),
                networkParams.getIpv6DefaultGateway(),
                networkIF.getName(),
                networkParams.getDnsServers(),
                networkIF.getSpeed()

        );
        List<oshi.hardware.PowerSource> powerSources = systemInfo.getHardware().getPowerSources();
        PowerSource powerSource = new PowerSource(
                powerSources.get(0).getCurrentCapacity(),
                powerSources.get(0).getPowerUsageRate(),
                powerSources.get(0).getDeviceName(),
                powerSources.get(0).getTemperature(),
                powerSources.get(0).getAmperage(),
                powerSources.get(0).getTimeRemainingInstant(),
                powerSources.get(0).getVoltage(),
                powerSources.get(0).getManufacturer()
        );

        Statistics statistics = new Statistics(hardwareInfo, softwareInfo, networkInfo, powerSource);

        return statistics;
    }
}
