import com.clockworkant.base.rxschedulers.ImmediateSchedulerProvider
import com.clockworkant.boilerplate.MainPresenter
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@Suppress("IllegalIdentifier")
class MainPresenterTest {

    @Mock
    private lateinit var view: MainPresenter.View

    private lateinit var presenter: MainPresenter

    private val button = PublishSubject.create<Any>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = MainPresenter(view, ImmediateSchedulerProvider)

        whenever(view.onButtonClicked()).thenReturn(button)

        presenter.subscribe()
    }

    @Test
    fun `on button pressed show button pressed`() {
        //When
        button.onNext("")

        //Then
        verify(view).showButtonClicked()
    }

    @Test
    fun `on button pressed with error show button pressed error`() {
        //When
        val throwable = Throwable("we have a problem")
        button.onError(throwable)

        //Then
        verify(view).showError(throwable)
    }
}