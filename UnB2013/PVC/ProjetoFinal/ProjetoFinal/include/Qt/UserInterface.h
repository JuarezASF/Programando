#ifndef PROJETOFINAL_H
#define PROJETOFINAL_H

#include <string>

#include <QMainWindow>

#include <QCursor>
#include <QMouseEvent>

#include <opencv2/core/core.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/video/background_segm.hpp>

#include "cv2qtimage.h"
#include "myString.h"
#include "secondWindow.h"
#include "MyKalmanFilter.h"
#include "ColorDetection.h"

using namespace  cv;

namespace Ui {
class ProjetoFinal;
}

extern int iTau;
extern Scalar minCorHSV;
extern Scalar maxCorHSV;
extern bool CONTROL_BGSub;
extern bool CONTROL_COLORDETECTION;
extern bool CONTROL_FILTER_GAUSSIAN;
extern int  SizeGaussFilter;



class ProjetoFinal : public QMainWindow
{
    Q_OBJECT



private:
    //Qt objects
    Ui::ProjetoFinal *ui;
    QCursor *mouseCursor;
    QTimer *timer;
    secondWindow *auxiliarWindow;

    //openCV objects
    VideoCapture *video;
    Mat currentFrame;
    cv::BackgroundSubtractorMOG2 *bg;

    //C++ objects
    string *fileNames;

    //C objects
    int nVideos;
    int videoAtual;

public:
    //controle de funcionalidade
    bool futureMode, pastMode;

    //controle de execução
    bool run;

public:
    explicit ProjetoFinal(QWidget *parent = 0);
    ~ProjetoFinal();

    //seta imagem principal
    void setInputImg(const cv::Mat &img);
    void setOutputImg(const cv::Mat &img);

    //retorna posição do mouse
    QPoint getMousePos();

    //funções para imprimir texto(atualmente são todas iguais)
    void report(string text);
    void reportGood(string text);
    void reportBad(string text);
    void reportWarning(string text);

private:
    //inicializa vídeo
    void initVideo();
    void initVideo(const char *fileName);
    void initBG();
    void uptdateBG();

    //controle de fluxo
    void pauseVideo();
    void runVideo();

    void initSecondWindow();

    //Módulo de Detecção de cores
    void updateColorTrackBars();


private slots:

    //controle de funcionalidade
    void on_pastButtom_stateChanged(int arg1);
    void on_futureButtom_stateChanged(int arg1);

    //controle de fluxo
    void on_pauseButtom_clicked();
    void on_restartButtom_clicked();

    //===>FUNÇÃO DE PROCESSAMENTO-----PRINCIPAL----<===
    void process();
    void on_speedSlider_sliderMoved(int position);
    void on_runDemoKalman_clicked();
    void on_videoFileOption_currentIndexChanged(int index);
    void on_closeButtom_clicked();
    void on_clearButtom_clicked();
    void on_ColorMinChannelSlider_valueChanged(int value);
    void on_ColorMaxChannelSlider_valueChanged(int value);
    void on_ColorMinChannelOption_currentIndexChanged(int index);
    void on_ColorMaxChannelOption_currentIndexChanged(int index);
    void on_DefinedColorOption_currentIndexChanged(int index);
    void on_BackGroundSubButtom_clicked();
    void on_ColorDetectionButtom_clicked();
    void on_BorrarButtom_clicked();
    void on_SizeOfGaussian_currentIndexChanged(int index);
    };

#endif // PROJETOFINAL_H




