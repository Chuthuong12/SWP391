
import java.util.Arrays;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import utils.RoleAndRequestMapper;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
/**
 * Web application lifecycle listener.
 *
 * @author tiendang
 */
import model.Role.Type;

public class ApplicationServletContext implements ServletContextListener {

    protected RoleAndRequestMapper roleAndRequestMapper = new RoleAndRequestMapper();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        registerResourcePage();
    }

    public void registerResourcePage() {
        //Insert .jsp or Servlet mapping here NOTE: (Without slash /)!!!!
        //Dasboard 
        roleAndRequestMapper.register("SWP391/DashboardServlet", Arrays.asList(Type.admin));
        roleAndRequestMapper.register("adminDashboard.jsp", Arrays.asList(Type.admin));

        //Slider 
        roleAndRequestMapper.register("SWP391/add-slider", Arrays.asList(Type.admin, Type.marketing));
        roleAndRequestMapper.register("SWP391/edit-slider", Arrays.asList(Type.admin, Type.marketing));
        roleAndRequestMapper.register("SWP391/hide-slider", Arrays.asList(Type.admin, Type.marketing));
        roleAndRequestMapper.register("SWP391/show-slider", Arrays.asList(Type.admin, Type.marketing));
        roleAndRequestMapper.register("SWP391/search-slider", Arrays.asList(Type.admin, Type.marketing));
        roleAndRequestMapper.register("SWP391/slider-list", Arrays.asList(Type.admin, Type.marketing));
        roleAndRequestMapper.register("SWP391/slider-detail", Arrays.asList(Type.admin, Type.marketing));

        roleAndRequestMapper.register("SWP391/AddNewSlider.jsp", Arrays.asList(Type.admin, Type.sale));
        roleAndRequestMapper.register("SWP391/EditSlider.jsp", Arrays.asList(Type.admin, Type.sale));
        roleAndRequestMapper.register("SWP391/SliderDetail.jsp", Arrays.asList(Type.admin, Type.sale));
        roleAndRequestMapper.register("SWP391/SliderList.jsp", Arrays.asList(Type.admin, Type.sale));

        //Lesson
        roleAndRequestMapper.register("SWP391/AddLessonServlet", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/subject-lesson", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/subject-lesson-detail", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/ViewAddLessonServlet", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/ViewLessonDetailServlet", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/filter-lesson", Arrays.asList(Type.admin, Type.expert));

        roleAndRequestMapper.register("SWP391/addLesson.jsp", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/SubjectLesson.jsp", Arrays.asList(Type.admin, Type.expert));

        //User
        roleAndRequestMapper.register("SWP391/UserList", Arrays.asList(Type.admin));
        roleAndRequestMapper.register("SWP391/UserDetail", Arrays.asList(Type.admin));
        roleAndRequestMapper.register("SWP391/FilterUser", Arrays.asList(Type.admin));

        roleAndRequestMapper.register("SWP391/UserList.jsp", Arrays.asList(Type.admin));
        roleAndRequestMapper.register("SWP391/UserDetail.jsp", Arrays.asList(Type.admin));

        //Quizzes
        roleAndRequestMapper.register("SWP391/add-quiz", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/edit-quiz-async", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/edit-quiz", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/hide-quiz", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/quiz-detail", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/quiz-list", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/show-quiz", Arrays.asList(Type.admin, Type.expert));

        roleAndRequestMapper.register("SWP391/QuizList.jsp", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/ListLessons.jsp", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/QuizDetail.jsp", Arrays.asList(Type.admin, Type.expert));

        roleAndRequestMapper.register("SWP391/DoQuizzServlet", Arrays.asList(Type.admin, Type.expert, Type.customer));
        roleAndRequestMapper.register("SWP391/PracticeQuizzServlet", Arrays.asList(Type.admin, Type.expert, Type.customer));
        roleAndRequestMapper.register("SWP391/ReviewQuizzServlet", Arrays.asList(Type.admin, Type.expert, Type.customer));
        roleAndRequestMapper.register("SWP391/QuizDetailReview", Arrays.asList(Type.admin, Type.expert, Type.customer));
        roleAndRequestMapper.register("SWP391/SearchQuizController", Arrays.asList(Type.admin, Type.expert, Type.customer));
        roleAndRequestMapper.register("SWP391/simulation-exam", Arrays.asList(Type.admin, Type.expert, Type.customer));

        roleAndRequestMapper.register("SWP391/doQuizz.jsp", Arrays.asList(Type.admin, Type.expert, Type.customer));
        roleAndRequestMapper.register("SWP391/QuizDetailReview.jsp", Arrays.asList(Type.admin, Type.expert, Type.customer));
        roleAndRequestMapper.register("SWP391/reviewQuizz.jsp", Arrays.asList(Type.admin, Type.expert, Type.customer));
        roleAndRequestMapper.register("SWP391/SimulationExam.jsp", Arrays.asList(Type.admin, Type.expert, Type.customer));

        //Question
        roleAndRequestMapper.register("SWP391/delete-question", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/FilterQuestion", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/InsertQuestion", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/question-detail", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/QuestionListAdminServlet", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/QuestionList", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/search-question", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/updateQuestion", Arrays.asList(Type.admin, Type.expert));

        roleAndRequestMapper.register("SWP391/QuestionList.jsp", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/QuestionListAdmin.jsp", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/questionDetail.jsp", Arrays.asList(Type.admin, Type.expert));

        //Subject done
        roleAndRequestMapper.register("SWP391/CreateController", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/CreateFormController", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/EditSubjectController", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/SubjectListAdmin", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/SubjectDetailAdminController", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/UpdateController", Arrays.asList(Type.admin, Type.expert));

        roleAndRequestMapper.register("SWP391/CreateSubject.jsp", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/Detail.jsp", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/SubjectListAdmin.jsp", Arrays.asList(Type.admin, Type.expert));
//        roleAndRequestMapper.register("SWP391/SubjectDetail.jsp", Arrays.asList(Type.admin, Type.expert));

        //Dimensions Coursecontent done
        roleAndRequestMapper.register("SWP391/CreateDimension", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/DeleteDimension", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/EditDimension", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/delete-dimension", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/dimension-list", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/update-dimension", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/update_dimension-async", Arrays.asList(Type.admin, Type.expert));

        roleAndRequestMapper.register("SWP391/CreateDimension.jsp", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/EditDimension.jsp", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/DimensionCreate.jsp", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/DimensionAndPackage.jsp", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/UpdateDimensionAsync.jsp", Arrays.asList(Type.admin, Type.expert));

        //Package Price 
        roleAndRequestMapper.register("SWP391/AddPackageInSubject", Arrays.asList(Type.admin));
        roleAndRequestMapper.register("SWP391/DeletePackageInSubject", Arrays.asList(Type.admin));
        roleAndRequestMapper.register("SWP391/update_price_async", Arrays.asList(Type.admin));
        roleAndRequestMapper.register("SWP391/PricePackageUpdateInSubject", Arrays.asList(Type.admin));

        roleAndRequestMapper.register("SWP391/AddPackagePriceInSubject.jsp", Arrays.asList(Type.admin));
        roleAndRequestMapper.register("SWP391/InforPackagePrice.jsp", Arrays.asList(Type.admin));
        roleAndRequestMapper.register("SWP391/EditPricePackage.jsp", Arrays.asList(Type.admin));

        //Post Detail
        roleAndRequestMapper.register("SWP391/add-post", Arrays.asList(Type.admin, Type.marketing));
        roleAndRequestMapper.register("SWP391/edit-post", Arrays.asList(Type.admin, Type.marketing));
        roleAndRequestMapper.register("SWP391/post-detail", Arrays.asList(Type.admin, Type.marketing, Type.customer));
        roleAndRequestMapper.register("SWP391/post-list", Arrays.asList(Type.admin, Type.marketing, Type.customer));
        roleAndRequestMapper.register("SWP391/testEdit", Arrays.asList(Type.admin, Type.marketing));

        roleAndRequestMapper.register("SWP391/AddNewPost.jsp", Arrays.asList(Type.admin, Type.marketing));
        roleAndRequestMapper.register("SWP391/EditPost.jsp", Arrays.asList(Type.admin, Type.marketing));
        roleAndRequestMapper.register("SWP391/PostDetail.jsp", Arrays.asList(Type.admin, Type.marketing, Type.customer));
        roleAndRequestMapper.register("SWP391/BlogList.jsp", Arrays.asList(Type.admin, Type.marketing, Type.customer));

        //Practice done
        roleAndRequestMapper.register("SWP391/filter-practice-async", Arrays.asList(Type.customer));
        roleAndRequestMapper.register("SWP391/practice-list", Arrays.asList(Type.customer));
        roleAndRequestMapper.register("SWP391/practiceQuiz", Arrays.asList(Type.customer));
        roleAndRequestMapper.register("SWP391/PraticeDetail", Arrays.asList(Type.customer));

        roleAndRequestMapper.register("SWP391/PracticeListAsync.jsp", Arrays.asList(Type.customer));
        roleAndRequestMapper.register("SWP391/PracticeList.jsp", Arrays.asList(Type.customer));
        roleAndRequestMapper.register("SWP391/PracticeDetail.jsp", Arrays.asList(Type.customer));

        //PricePackage done
        roleAndRequestMapper.register("SWP391/create-pricePackage", Arrays.asList(Type.admin));
        roleAndRequestMapper.register("SWP391/delete-pricePackage", Arrays.asList(Type.admin));
        roleAndRequestMapper.register("SWP391/update-pricePackage", Arrays.asList(Type.admin));

        roleAndRequestMapper.register("SWP391/PricePackageCreate.jsp", Arrays.asList(Type.admin));
        roleAndRequestMapper.register("SWP391/PricePackageUpdate.jsp", Arrays.asList(Type.admin));

        //Question done
        roleAndRequestMapper.register("SWP391/delete-question", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/FilterQuestion", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/InsertQuestion", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/QuestionListAdminServlet", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/QuestionList", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/updateQuestion", Arrays.asList(Type.admin, Type.expert));

        roleAndRequestMapper.register("SWP391/QuestionList.jsp", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/questionDetail.jsp", Arrays.asList(Type.admin, Type.expert));
        roleAndRequestMapper.register("SWP391/QuestionListAdmin.jsp", Arrays.asList(Type.admin, Type.expert));

        //Registration done
        roleAndRequestMapper.register("SWP391/edit-status-regis", Arrays.asList(Type.admin, Type.sale));
        roleAndRequestMapper.register("SWP391/filter-registration", Arrays.asList(Type.admin, Type.sale));
        roleAndRequestMapper.register("SWP391/regis-list", Arrays.asList(Type.admin, Type.sale));

        roleAndRequestMapper.register("SWP391/RegistrationList.jsp", Arrays.asList(Type.admin, Type.sale));

        //(slider,lesson,user ,quizzes,question,su+bject,Dimensions Course,Package price ,Post,Practices,PricePackage,Question,Registration )
        

//        roleAndRequestMapper.register("SWP391/normal-lession.jsp", Arrays.asList(Type.admin, Type.customer, Type.expert, Type.marketing, Type.sale));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
