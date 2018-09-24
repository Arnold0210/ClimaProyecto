package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Enumeration;
import gnu.io.SerialPort;
//import gnu.io.SerialPortEventListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.controllerClima;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import model.DAO.CiudadDAO;
import model.DTO.Ciudad;

public class vista extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private OutputStream output = null;
	private InputStream input = null;
	SerialPort serialPort;
	private final String PORT_NAME = "COM7";
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;
	JButton conectar, desconectar;

	public static void main(String[] args) {
		vista v = new vista();
		v.setVisible(true);
		v.setSize(400, 300);
	}

	private class ImplementoRunnable implements Runnable {
//		char date_1, date_2, date_3, date_4;

		controllerClima controllerClima = new controllerClima();

		public void run() {
			while (true) {
				try {
					double firstHumidity, secondHumidity, firstTemperature, secondTemperature;
					output.write('T');
					Thread.sleep(500);
					firstTemperature = Character.getNumericValue((char) input.read());
					secondTemperature = Character.getNumericValue((char) input.read());
					firstHumidity = Character.getNumericValue((char) input.read());
					secondHumidity = Character.getNumericValue((char) input.read());
					if (firstHumidity >= 0 && firstHumidity <= 9 && secondHumidity >= 0 && secondHumidity <= 9
							&& firstTemperature >= 0 && secondTemperature <= 9) {
						double temperature = firstTemperature*10+secondTemperature;
						double humidity = (double)(firstHumidity*10+secondHumidity);
						Ciudad city = new Ciudad();
						CiudadDAO citydao = new CiudadDAO();
						city=citydao.select(1);
						controllerClima.createClima(true, humidity, temperature, city);
						System.out.println(temperature+" "+humidity+"\n");
					}
					repaint();
				} catch (Exception e1) {

				}
			}
		}
	}

	public vista() {
		setTitle("Clima");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 366, 365);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Thread timer = new Thread(new ImplementoRunnable());
		timer.start();
		timer.interrupt();
		conectar = new JButton("Conectar");
		conectar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				CommPortIdentifier portId = null;
				@SuppressWarnings("rawtypes")
				Enumeration PortEnum = CommPortIdentifier.getPortIdentifiers();
				while (PortEnum.hasMoreElements()) {
					CommPortIdentifier currPortId = (CommPortIdentifier) PortEnum.nextElement();
					if (PORT_NAME.equals(currPortId.getName())) {
						portId = currPortId;
						try {
							serialPort = (SerialPort) portId.open("puerto serial", TIME_OUT);
							serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
									SerialPort.PARITY_NONE);
							serialPort.setDTR(true);
							output = serialPort.getOutputStream();
							input = serialPort.getInputStream();
							desconectar.setEnabled(true);
							conectar.setEnabled(false);
							timer.resume();
						} catch (PortInUseException e1) {
							e1.printStackTrace();
						} catch (UnsupportedCommOperationException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						break;
					}
				}
			}
		});
		conectar.setBounds(38, 63, 89, 23);
		contentPane.add(conectar);

		desconectar = new JButton("Desconectar");
		desconectar.setEnabled(false);
		desconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timer.interrupt();
				serialPort.close();
				desconectar.setEnabled(false);
				conectar.setEnabled(true);
			}
		});

		desconectar.setBounds(205, 63, 128, 23);
		contentPane.add(desconectar);
	}
}